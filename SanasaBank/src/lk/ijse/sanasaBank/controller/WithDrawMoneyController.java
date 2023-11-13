package lk.ijse.sanasaBank.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.model.DepositeModel;
import lk.ijse.sanasaBank.model.WithdrawModel;
import lk.ijse.sanasaBank.to.DepositeMoney;
import lk.ijse.sanasaBank.to.WithDrawMoney;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WithDrawMoneyController implements Initializable {
    public TextField txtTransId;
    public TextField txtActId;
    public TextField txtDepositeName;
    public TextField txtDepositeAmount;
    public TextField txtDate;
    public Label txtActOwnerName;
    public Label txtActOwnerName1;
    public Label txtBalance;
    public Label lblActId;
    public Label lblAmount;
    @FXML
    private Label lblAdminNameOnACtion;

    @FXML
    private AnchorPane pane;

    String actId;

    String transactionId;

    String WithdrawName;

    Double WithdrawAmoubt;

    String date;

    public WithDrawMoneyController() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void WithdrawOnAction(ActionEvent event) {

    }

    @FXML
    void accountsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
    }

    @FXML
    void depositeMoneyOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    @FXML
    void homeOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }

    @FXML
    void loansOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.IssueLoan, pane);
    }

    @FXML
    void memberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
    }

    @FXML
    void paymentsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Payments, pane);
    }

    @FXML
    void reportsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Reports, pane);
    }

    @FXML
    void transactionOnAction(ActionEvent event) {

    }

    public void searchAccountOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        actId=txtActId.getText();
        lblActId.setText(null);
        WithDrawMoney withDrawMoney=new WithDrawMoney(actId);

        WithdrawModel withdrawModel=new WithdrawModel();
        if(withdrawModel.setDetail(withDrawMoney)){
            txtActOwnerName1.setText(withdrawModel.actType);
            txtBalance.setText(withdrawModel.balance);
        }

        WithdrawModel withdrawModel1=new WithdrawModel();
        boolean b= withdrawModel1.setDetail(withDrawMoney);
        if(b){
            WithdrawModel.setActName();
            txtActOwnerName.setText(WithdrawModel.ownerName);
        }else {
        lblActId.setText("Invalid Account ID");
        }

    }


    public void txtDepositeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            if(txtActOwnerName.getText()!=("")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Plese Verify The Acount By searching");
                alert.initOwner(Navigation.window);
                alert.show();
        }else{
                Pattern amountMatcher = Pattern.compile("^[1-9]\\d{3,}$");
                Matcher emailMatcher = amountMatcher.matcher(txtDepositeAmount.getText());
                boolean isEmailMatches = emailMatcher.matches();


                if(txtActId.getText()==null|| txtTransId.getText().equals("") ||txtDepositeName.getText()==null||txtDepositeAmount.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Plese Fill The Empty Braktes");
                    alert.initOwner(Navigation.window);
                    alert.show();
                }else {
                    if(isEmailMatches){
                        if(lblActId.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Plese Verify The Acount By searching");
                            alert.initOwner(Navigation.window);
                            alert.show();

                        }else{

                            actId=txtActId.getText();
                            transactionId=txtTransId.getText();
                            WithdrawName=txtDepositeName.getText();
                            WithdrawAmoubt= Double.valueOf(txtDepositeAmount.getText());
                            date=txtDate.getText();
                            WithDrawMoney withDrawMoney=new WithDrawMoney(actId, transactionId, WithdrawName, WithdrawAmoubt,date);
                            boolean b= WithdrawModel.setAccountDetail(withDrawMoney);
                        }
                    }else{
                        lblAmount.setText("Insafisant Amount");
                    }
                }
            }



    }
    public  String genareteMectMatirialId() throws SQLException, ClassNotFoundException {
        String sql="SElect TID FROM transaction ORDER BY TID DESC LIMIT 1";
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        String id = "";

        if(result.next()){
            id = result.getString(1);
        }

        String [] split = id.split("T0");
        int temp = Integer.parseInt(split[1]);
        temp+=1;
        return "T0"+temp;
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String id = genareteMectMatirialId();
        txtTransId.setText(id);
    }
    public void showDate(){
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
        String date=s.format(d);
        txtDate.setText(date);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDate();
        try {
            loadNextId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
