package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.model.ViewAllAccountModel;
import lk.ijse.sanasaBank.model.ViewAllLoanModel;
import lk.ijse.sanasaBank.model.ViewAllMemberModel;
import lk.ijse.sanasaBank.to.*;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class ReportsController implements Initializable {

    public TextField txtAdminCode;
    public JFXButton btnLoanReport;
    public JFXButton btnAccountReport;
    public JFXButton btnCustomerReport;
    public Label lblAdminCode;
    @FXML
    private TableView<ViewAllMember> tblMember;
    @FXML
    private TableColumn<ViewAllMember, String> clmAddress;

    @FXML
    private TableColumn<ViewAllMember, String> clmAge;

    @FXML
    private TableColumn<ViewAllMember, String> clmContact;

    @FXML
    private TableColumn<ViewAllMember, String> clmEmail;

    @FXML
    private TableColumn<ViewAllMember, String> clmGender;

    @FXML
    private TableColumn<ViewAllMember, String> clmId;

    @FXML
    private TableColumn<ViewAllMember, String> clmName;

    @FXML
    private TableColumn<ViewAllMember, String> clmNic;


    public TableView <ViewAllAccount>tblAccount;
    public TableColumn <ViewAllAccount, String>clmActId1;
    public TableColumn <ViewAllAccount, String>clmActType1;
    public TableColumn <ViewAllAccount, String>clmMemberId1;
    public TableColumn <ViewAllAccount, Double>clmBalance1;
    public TableColumn <ViewAllAccount, String>clmDate1;




    @FXML
    private TableView<ViewAllLoan> tblLoan;


    @FXML
    private TableColumn<ViewAllLoan, String> clmActId2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmAmount2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmDate2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmInterest2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmLTypeId2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmLoanId2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmPeriod2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmReason2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmRemaingAmount2;

    @FXML
    private TableColumn<ViewAllLoan, String> clmTotal2;






    @FXML
    private Label lblAdminName;

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
    void reportsOnAction(ActionEvent event) {

    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            btnAccountReport.setDisable(true);
            btnLoanReport.setDisable(true);
            btnCustomerReport.setDisable(true);


        clmId.setCellValueFactory(new PropertyValueFactory<ViewAllMember, String>("custId"));
        clmName.setCellValueFactory(new PropertyValueFactory<ViewAllMember, String>("name"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<ViewAllMember, String>("email"));
        clmGender.setCellValueFactory(new PropertyValueFactory<ViewAllMember, String>("gender"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<ViewAllMember, String>("address"));
        clmAge.setCellValueFactory(new PropertyValueFactory<ViewAllMember, String>("age"));
        clmContact.setCellValueFactory(new PropertyValueFactory<ViewAllMember, String>("contact"));
        clmNic.setCellValueFactory(new PropertyValueFactory<ViewAllMember, String>("nic"));
        ViewAllMemberModel viewAllMemberModel=new ViewAllMemberModel();
        try {
            ArrayList<AddMember> viewAll = viewAllMemberModel.viewAll();

            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(viewAll);

            tblMember.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        clmActId1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actId"));
        clmActType1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actTypeId"));
        clmMemberId1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("custId"));
        clmBalance1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, Double>("balance"));
        clmDate1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("date"));

        ViewAllAccountModel viewAllAccountModel=new ViewAllAccountModel();
        try {
            ArrayList<OpenAccount> viewAllAccounts = viewAllAccountModel.viewAllAccounts();
            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(viewAllAccounts);

            tblAccount.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }


        clmLoanId2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("lid"));
        clmLTypeId2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("lTypeId"));
        clmActId2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("actId"));
        clmPeriod2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("Period"));
        clmDate2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("date"));
        clmAmount2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("amount"));
        clmInterest2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("interest"));
        clmTotal2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("Total"));
        clmRemaingAmount2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("RemaingAmount"));
        clmReason2.setCellValueFactory(new PropertyValueFactory<ViewAllLoan, String>("Reason"));

        try {
            ArrayList<ViewAllLoan> viewAll = ViewAllLoanModel.viewAllLoans();
            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(viewAll);
            tblLoan.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void custReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
       HashMap<String ,Object> h=new HashMap<>();
       h.put("CustName","Rishika");

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/lk/ijse/sanasaBank/jasper/CustomerReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(resourceAsStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, h, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void actReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        HashMap<String ,Object> h=new HashMap<>();
        h.put("CustName","Rishika");

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/lk/ijse/sanasaBank/jasper/AccountReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(resourceAsStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, h, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

    public void loanReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        HashMap<String ,Object> h=new HashMap<>();
        h.put("CustName","Rishika");

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/lk/ijse/sanasaBank/jasper/LoanReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(resourceAsStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, h, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);

    }

    public void txtAdminCodeOnAction(ActionEvent actionEvent) {
        if(txtAdminCode.getText().equals("sanasa")){
            btnCustomerReport.setDisable(false);
            btnLoanReport.setDisable(false);
            btnAccountReport.setDisable(false);
        }else{
            lblAdminCode.setText("Invalid Admin Code");
            txtAdminCode.setStyle("-fx-text-box-border: #ff0000; -fx-focus-color: #ff0000;");
            btnCustomerReport.setDisable(true);
            btnLoanReport.setDisable(true);
            btnAccountReport.setDisable(true);
        }
    }
}
