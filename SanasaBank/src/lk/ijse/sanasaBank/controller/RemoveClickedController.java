package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.model.*;
import lk.ijse.sanasaBank.to.*;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RemoveClickedController implements Initializable {
    public Label lblAdminName;
    public JFXComboBox cmbMemId;
    public JFXComboBox cmbMemName;
    public TableColumn clmDate;



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


    public TableView<ViewAllLoan> tblLoan;
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


    public TableView <ViewAllAccount>tblAccount;
    public TableColumn <ViewAllAccount, String>clmActId1;
    public TableColumn <ViewAllAccount, String>clmActType1;
    public TableColumn <ViewAllAccount, String>clmMemberId1;
    public TableColumn <ViewAllAccount, Double>clmBalance1;
    public TableColumn <ViewAllAccount, String>clmDate1;


    @FXML
    private AnchorPane pane;

    @FXML
    void memberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
    }

    @FXML
    void accountsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
    }

    @FXML
    void loansOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.IssueLoan, pane);
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
    void addMemberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
    }

    @FXML
    void searchMemberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.SearchedClicked, pane);
    }

    @FXML
    void viewMemberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewMembers, pane);
    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    public void removeMemberOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.RemoveMember, pane);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }



    public void cmbMemIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


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
            ArrayList<ViewAllLoan> viewAll = IssueLoanModel.viewAllLoans();

            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(viewAll);

            tblLoan.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }













        //colom updta krnna loan eke
        IssueLoanModel issueLoanModel=new IssueLoanModel();
        String name= (String) cmbMemName.getValue();
        String id= (String) cmbMemId.getValue();
        searchMember searchMember=new searchMember(id, name);
        issueLoanModel.viewAllAccounts(searchMember);

        clmActId1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actId"));
        clmActType1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actTypeId"));
        clmMemberId1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("custId"));
        clmBalance1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, Double>("balance"));
        clmDate1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("date"));
        IssueLoanModel issueLoanModel2=new IssueLoanModel();

        ArrayList<OpenAccount> openAccounts = issueLoanModel2.viewAllAccounts(searchMember);

        ObservableList observableList= FXCollections.observableArrayList();
        observableList.addAll(openAccounts);
        System.out.println(observableList);

        tblAccount.setItems(observableList);

    }

    public void cmbMemNameOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String name= (String) cmbMemName.getValue();
        String id= (String) cmbMemId.getValue();
        searchMember searchMember=new searchMember(id, name);
        //colom updta krnna loan eke
        clmActId1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actId"));
        clmActType1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actTypeId"));
        clmMemberId1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("custId"));
        clmBalance1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, Double>("balance"));
        clmDate1.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("date"));

        IssueLoanModel issueLoanModel2=new IssueLoanModel();

        ArrayList<OpenAccount> openAccounts = issueLoanModel2.viewAllAccountsName(searchMember);

        ObservableList observableList= FXCollections.observableArrayList();
        observableList.addAll(openAccounts);
        System.out.println(observableList);

        tblAccount.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> memid = null;
        try {
            memid = RemoveMem.memId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        cmbMemId.setItems(memid);

        try {
            ObservableList<String> memName = RemoveMem.memName();
            cmbMemName.setItems(memName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


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

            ObservableList observableList1= FXCollections.observableArrayList();
            observableList1.addAll(viewAll);

            tblMember.setItems(observableList1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
            System.out.println("Tru una");
            ArrayList<ViewAllLoan> viewAll = ViewAllLoanModel.viewAllLoans();
            ObservableList observableList= FXCollections.observableArrayList();
            System.out.println(viewAll);
            observableList.addAll(viewAll);
            tblLoan.setItems(observableList);
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


    }
}
