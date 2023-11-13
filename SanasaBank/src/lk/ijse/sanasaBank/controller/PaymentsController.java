package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.model.PayModel;
import lk.ijse.sanasaBank.to.Pay;
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

public class PaymentsController implements Initializable {
    public Label lblAdminName;
    public JFXComboBox cbmLoanId;
    public Label lblActId;
    public Label lblAmount;
    public Label lblInterest;
    public Label lblTot;
    public Label lblRemainAmount;
    public TextField txtPayID;

    public TextField txtAmount;
    public Label lblRemainAmount1;
    public ToggleGroup payMethod;
    public TextField txtChequeNum;
    public TextField txtDate;
    public RadioButton rbnCash;
    public RadioButton rbnOnline;
    public RadioButton rbnCheque;
    public Label lblAmount2;
    public Label lblChequeNum;

    @FXML
    private AnchorPane pane;
    @FXML
    void accountsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
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
     String loanId;
     String payId;
     Double amount;
     String payMethod1;
     String chequeNum;
     String date;
    @FXML
    void payOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        lblAmount2.setText(null);
        lblChequeNum.setText(null);
        if(lblActId.getText()==null || txtPayID.getText()==null||txtAmount.getText()==null||cbmLoanId.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Make Sure to Fill Al Blanks");
            alert.initOwner(Navigation.window);
            alert.show();
        }else{
            Pattern amountPattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");
            Matcher amountlMatcher = amountPattern.matcher(txtAmount.getText());
            boolean isAmountMatches = amountlMatcher.matches();
            if(isAmountMatches){
                loanId= (String) cbmLoanId.getValue();
                payId=txtPayID.getText();
                amount= Double.parseDouble(txtAmount.getText());

                if(rbnCash.isSelected()){
                    payMethod1="Cash";
                } else if (rbnOnline.isSelected()) {
                    payMethod1="Online";
                }else {
                    payMethod1="Cheque";
                }
                chequeNum=txtChequeNum.getText();
                date=txtDate.getText();
                Pay pay=new Pay(loanId,payId,amount,payMethod1,chequeNum,date);
                System.out.println(payMethod1);
                if(rbnCheque.isSelected()){
                    PayModel.payCheque(pay);
                }else {
                    PayModel.updateLoan(pay);
                }
            }else {
                lblAmount2.setText("Invalid Amount");
            }

        }
    }

    @FXML
    void paymentsOnAction(ActionEvent event) {


    }

    @FXML
    void reportsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Reports, pane);
    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    public void cbmSetLoanDetail(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Pay pay=new Pay((String) cbmLoanId.getValue());
        PayModel.setLoanDetail(pay);
        lblActId.setText(PayModel.actIdFor);
        lblAmount.setText(PayModel.amount);
        lblInterest.setText(PayModel.interest);
        lblRemainAmount.setText(PayModel.remain);
        lblRemainAmount1.setText(String.valueOf(PayModel.monthInst));
        lblTot.setText(PayModel.tot);
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
        lblActId.setText(null);
        try {
            ObservableList<String> loanID = PayModel.LoanID();

            cbmLoanId.setItems(loanID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public  String genareteMectMatirialId() throws SQLException, ClassNotFoundException {
        String sql="SElect PayID FROM instalmentpayment ORDER BY PayID DESC LIMIT 1";
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        String id = "";

        if(result.next()){
            id = result.getString(1);
        }

        String [] split = id.split("P0");
        int temp = Integer.parseInt(split[1]);
        temp+=1;
        return "P0"+temp;
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String id = genareteMectMatirialId();
        txtPayID.setText(id);
    }

    public void payOnAction1(ActionEvent actionEvent) {
    }
}
