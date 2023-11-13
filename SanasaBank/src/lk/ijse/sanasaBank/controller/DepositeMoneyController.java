package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.model.DepositeModel;
import lk.ijse.sanasaBank.to.DepositeMoney;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DepositeMoneyController implements Initializable {
    public TextField txtTransId;
    public TextField txtActId;
    public Label txtActOwnerName;
    public Label txtActOwnerName1;
    public TextField txtDepositeName;
    public TextField txtDepositeAmount;
    public TextField txtDate;
    public JFXComboBox cmbTransactionType;
    public Label lblActId;
    public Label lblAmount;
    public JFXButton btnPrint;
    @FXML
    private Label lblAdminName;

    @FXML
    private AnchorPane pane;

    String actId;
    String transactionId;

    String depositeName;

    Double depositeAmoubt;

    String transType;

    String date;

    @FXML
    void accountsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
    }

    @FXML
    void depositeOnAction(ActionEvent event) {

    }

    @FXML
    void homeOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }

    @FXML
    void loanOnAction(ActionEvent event) throws IOException {
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
    void transactionOnAction(ActionEvent event) throws IOException {

    }

    @FXML
    void withdrawOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.WithDraw, pane);
    }

    public void txtDepositeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InvocationTargetException {

       try {
           Pattern amountMatcher = Pattern.compile("^[1-9]\\d{3,}$");
           Matcher emailMatcher = amountMatcher.matcher(txtDepositeAmount.getText());
           boolean isEmailMatches = emailMatcher.matches();
           if(lblActId.getText()==null||txtDepositeName.getText()==null||cmbTransactionType.getValue()==null||txtTransId.getText()==null){
               Alert alert = new Alert(Alert.AlertType.ERROR, "Plese Fill The Empty Braktes");
               alert.initOwner(Navigation.window);
               alert.show();
           }else {
               if(isEmailMatches){
                   actId=txtActId.getText();
                   transactionId=txtTransId.getText();
                   depositeName=txtDepositeName.getText();
                   depositeAmoubt= Double.valueOf(txtDepositeAmount.getText());
                   transType= (String) cmbTransactionType.getValue();
                   date=txtDate.getText();
                   DepositeMoney depositeMoney=new DepositeMoney(actId, transactionId, depositeName, depositeAmoubt, transType,date);
                   if(cmbTransactionType.getValue()=="Cheque"){
                       boolean isSetAccountDetail = DepositeModel.chequeEnter(depositeMoney);
                       if(isSetAccountDetail){
                           try {
                               loadNextId();
                           } catch (SQLException | ClassNotFoundException e) {
                               throw new RuntimeException(e);
                           }
                           lblAmount.setText(null);
                            lblActId.setText(null);

                           Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deposite is Sucefull");
                           alert.initOwner(Navigation.window);
                           alert.show();
                       }else{
                           Alert alert = new Alert(Alert.AlertType.ERROR, "Somthing went Wrong");
                           alert.initOwner(Navigation.window);
                           alert.show();
                       }
                   }else {
                       boolean b = DepositeModel.setAccountDetail(depositeMoney);
                       if(b){
                           try {
                               loadNextId();
                           } catch (SQLException | ClassNotFoundException e) {
                               throw new RuntimeException(e);
                           }
                           Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deposite is Sucefull");
                           alert.initOwner(Navigation.window);
                           alert.show();
                       }Alert alert = new Alert(Alert.AlertType.ERROR, "Somthing went Wrong");
                       alert.initOwner(Navigation.window);
                       alert.show();
                   }

               }else {
                   lblAmount.setText("Insafisant Amount");
               }
           }
       }catch (SQLIntegrityConstraintViolationException q){
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Trans ID Already Exists");
           alert.initOwner(Navigation.window);
           alert.show();
       }
    }

    public void searchAccountOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


            actId=txtActId.getText();
            DepositeMoney depositeMoney=new DepositeMoney(actId);
            DepositeModel depositeModel=new DepositeModel();
            if(depositeModel.setDetail(depositeMoney)){
                txtActOwnerName1.setText(depositeModel.actType);
                DepositeModel.setActName();
                txtActOwnerName.setText(DepositeModel.actName);
            }else{
                lblActId.setText("Invalid Account ID");
            }


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
        ObservableList<String> paymentType= FXCollections.observableArrayList("Cash","Online Payment","Cheque");
        cmbTransactionType.setItems(paymentType);
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

    public void btnPrint(ActionEvent actionEvent) {

    }
}
