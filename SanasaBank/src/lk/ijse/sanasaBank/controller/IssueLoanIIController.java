package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.model.IssueLoanModel;
import lk.ijse.sanasaBank.model.ViewAllMemberModel;
import lk.ijse.sanasaBank.to.AddMember;
import lk.ijse.sanasaBank.to.IssueLoanII;
import lk.ijse.sanasaBank.to.ViewAllLoan;
import lk.ijse.sanasaBank.to.ViewAllMember;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IssueLoanIIController implements Initializable {
    public TextArea txtReason;
    public Label lblLoanType;
    public Label lblSettlePeriod;
    public Label lblAmount;
    public Label lblReason;
    @FXML
    private AnchorPane pane;



    @FXML
    private TableView<ViewAllLoan> tblLoan;


    @FXML
    private TableColumn<ViewAllLoan, String> clmActId;

    @FXML
    private TableColumn<ViewAllLoan, String> clmAmount;

    @FXML
    private TableColumn<ViewAllLoan, String> clmDate;

    @FXML
    private TableColumn<ViewAllLoan, String> clmInterest;

    @FXML
    private TableColumn<ViewAllLoan, String> clmLTypeId;

    @FXML
    private TableColumn<ViewAllLoan, String> clmLoanId;

    @FXML
    private TableColumn<ViewAllLoan, String> clmPeriod;

    @FXML
    private TableColumn<ViewAllLoan, String> clmReason;

    @FXML
    private TableColumn<ViewAllLoan, String> clmRemaingAmount;

    @FXML
    private TableColumn<ViewAllLoan, String> clmTotal;







    @FXML
    private JFXComboBox<String> clmLoanType;

    @FXML
    private JFXComboBox<String> clmLoanType1;

    @FXML
    private JFXButton conform;

    @FXML
    private Label lblInterest;

    @FXML
    private Label lblMonthlyInst;

    @FXML
    private Label lblTot;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    private String loanId;
    private String loanType;
    private String period;
    private int amount;
    private String date;
    private String reason;

    @FXML
    private TextField txtlLoanId;
    @FXML
    void accountOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
    }

    @FXML
    void homeOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }

    @FXML
    void issueLoanOnAction(ActionEvent event) {

    }

    @FXML
    void loanOnAction(ActionEvent event) {

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
    void searchLoanOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.SearchLoan, pane);
    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    @FXML
    void viewAllLoanOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.SearchLoan, pane);
    }

    public void conformInAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InvocationTargetException {


        /*

         */


        lblLoanType.setText(null);
        lblSettlePeriod.setText(null);
        lblAmount.setText(null);
        lblReason.setText(null);
        if(clmLoanType.getValue()!=null){
            if(clmLoanType1.getValue()!=null){
                if(txtAmount.getText().equals("")){
                    lblAmount.setText("Plese Enter Amount");
                }else if(txtReason.getText().equals("")){
                    lblReason.setText("Plese Enter the Reason");
                }else{

                    loanId=txtlLoanId.getText();
                    loanType=clmLoanType.getValue();
                    period= (clmLoanType1.getValue());

                    date=txtDate.getText();
                    reason=txtReason.getText();
                    if(clmLoanType.getValue()!=null){
                        if(clmLoanType1.getValue()!=null){
                            Pattern agePattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");
                            Matcher ageMatches = agePattern.matcher(txtAmount.getText());
                            boolean amountAgeMatches = ageMatches.matches();
                            if(amountAgeMatches){
                                amount= Integer.parseInt(txtAmount.getText());
                                System.out.println("Athulata awa");
                                amount = Integer.parseInt(txtAmount.getText());
                                if (amount >= 100000) {
                                    IssueLoanII issueLoanII = new IssueLoanII(loanId, loanType, period, date, amount, reason);
                                    try {
                                        boolean addLoanGurdian = IssueLoanModel.addLoanGurdian(issueLoanII);
                                        if (addLoanGurdian) {
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Loan Sucefully Issued ");
                                            alert.initOwner(Navigation.window);
                                            alert.show();
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong ");
                                            alert.initOwner(Navigation.window);
                                            alert.show();
                                        }
                                    } catch (SQLIntegrityConstraintViolationException q) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR, "Loan ID already exists");
                                        alert.initOwner(Navigation.window);
                                        alert.show();
                                    }
                                } else {
                                    lblAmount.setText("Amount Should Be More Than 100 000");
                                }
                            }else{
                                lblAmount.setText("Invalid Amount");
                            }
                                }else {

                            lblSettlePeriod.setText("Plese Select Settlement Period");
                                }

                    }else{
                        lblLoanType.setText("Pelese Select Type");
                    }
                }
            }else{
                lblSettlePeriod.setText("Plese Select Settlement Period");
            }
        }else{
            lblLoanType.setText("Plese Select Loan Type");
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

        ObservableList<String> period=FXCollections.observableArrayList("1 Year","3 Year","5 Year");
        clmLoanType1.setItems(period);

        clmLoanId.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("lid"));
        clmLTypeId.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("lTypeId"));
        clmActId.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("actId"));
        clmPeriod.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("Period"));
        clmDate.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("date"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("amount"));
        clmInterest.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("interest"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("Total"));
        clmRemaingAmount.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("RemaingAmount"));
        clmReason.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("Reason"));

        try {
            ArrayList<ViewAllLoan> viewAll = IssueLoanModel.viewAllLoans();

            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(viewAll);

            tblLoan.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        ObservableList<String> loanType = null;
        try {
            loanType = IssueLoanModel.loanType();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        clmLoanType.setItems(loanType);

    }


    public void loanTypeOnAction(ActionEvent actionEvent) {

    }


    public void setDetailOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        loanId=txtlLoanId.getText();

        loanType=clmLoanType.getValue();

        period= (clmLoanType1.getValue());

        amount= Integer.parseInt(txtAmount.getText());
        date=txtDate.getText();


        reason=txtReason.getText();
        IssueLoanII issueLoanII=new IssueLoanII(loanId, loanType, period, date,amount,  reason);
        IssueLoanModel.setLoanDetail(issueLoanII);
        lblInterest.setText(String.valueOf(IssueLoanModel.interest));
        lblTot.setText(String.valueOf(IssueLoanModel.tot));
        lblMonthlyInst.setText(String.valueOf(IssueLoanModel.monthInstalmemt));
    }

    public void ViewAllLoanOnACtion(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.ViewLoan, pane);
    }
    public  String genareteMectMatirialId() throws SQLException, ClassNotFoundException {
        String sql="SElect LID FROM loan ORDER BY LID DESC LIMIT 1";
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        String id = "";

        if(result.next()){
            id = result.getString(1);
        }

        String [] split = id.split("L0");
        int temp = Integer.parseInt(split[1]);
        temp+=1;
        return "L0"+temp;
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String id = genareteMectMatirialId();
        txtlLoanId.setText(id);
    }
}
