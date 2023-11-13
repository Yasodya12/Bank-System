package lk.ijse.sanasaBank.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.model.*;
import lk.ijse.sanasaBank.to.*;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class IssueLoanController implements Initializable {
    public TextField txtSearchId;
    public TextField txtSearchName;
    public TextField txtResultId;
    public TextField txtResultName;
    public TextField txtNic;
    public TextField txtActSearchId;
    public TableView tblAccount;
    public TableColumn clmActId;
    public TableColumn clmActType;
    public TableColumn clmMemberId;
    public TableColumn clmBalance;
    public TableColumn clmDate;
    public TextField txtActTypeSearch;
    public TextField txtBalanceSearch;
    public TextField txtDateSearch;
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
    @FXML
    private AnchorPane pane;

    String id;
    String name;

    String actId;
    public static String loandActId;
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
        Navigation.navigate(Route.ViewLoan, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        clmActId.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actId"));
        clmActType.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actTypeId"));
        clmMemberId.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("custId"));
        clmBalance.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, Double>("balance"));
        clmDate.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("date"));

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

    public void idOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtSearchName.setText(null);
        txtResultId.setText(null);
        txtResultName.setText(null);
        txtNic.setText(null);
        id=txtSearchId.getText();
        name=txtSearchName.getText();
        searchMember searchMember=new searchMember(id, name);


        IssueLoanModel issueLoanModel=new IssueLoanModel();

        if (!issueLoanModel.searchId(searchMember)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username Or ID may be same Username must be duplicated make sure to search with ID");
            alert.initOwner(Navigation.window);
            alert.show();
        } else {
            txtResultId.setText(issueLoanModel.reslultId);
            txtResultName.setText(issueLoanModel.resultName);
            txtNic.setText(issueLoanModel.nic);

        }


        issueLoanModel.viewAllAccounts(searchMember);

        clmActId.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actId"));
        clmActType.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actTypeId"));
        clmMemberId.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("custId"));
        clmBalance.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, Double>("balance"));
        clmDate.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("date"));
        IssueLoanModel issueLoanModel2=new IssueLoanModel();

        ArrayList<OpenAccount> openAccounts = issueLoanModel2.viewAllAccounts(searchMember);

        ObservableList observableList= FXCollections.observableArrayList();
        observableList.addAll(openAccounts);
        System.out.println(observableList);

        tblAccount.setItems(observableList);

    }

    public void nameOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtSearchId.setText(null);
        txtResultId.setText(null);
        txtResultName.setText(null);
        txtNic.setText(null);
        id=txtSearchId.getText();
        name=txtSearchName.getText();
        searchMember searchMember=new searchMember(id, name);


        IssueLoanModel issueLoanModel=new IssueLoanModel();

        if(issueLoanModel.searchNme(searchMember)){
            txtResultId.setText(issueLoanModel.reslultId);
            txtResultName.setText(issueLoanModel.resultName);
            txtNic.setText(issueLoanModel.nic);

            clmActId.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actId"));
            clmActType.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("actTypeId"));
            clmMemberId.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("custId"));
            clmBalance.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, Double>("balance"));
            clmDate.setCellValueFactory(new PropertyValueFactory<ViewAllAccount, String>("date"));

            IssueLoanModel issueLoanModel2=new IssueLoanModel();

            ArrayList<OpenAccount> openAccounts = issueLoanModel2.viewAllAccountsName(searchMember);

            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(openAccounts);
            System.out.println(observableList);

            tblAccount.setItems(observableList);

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username Or ID may be same Username must be duplicated make sure to search with ID");
            alert.initOwner(Navigation.window);
            alert.show();
        }
    }
    public static String actIdForLoan;
    public void actIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        actId=txtActSearchId.getText();

        SearchAccount searchAccount=new SearchAccount(actId);
        SearchAccountModel searchAccountModel=new SearchAccountModel();
        if(searchAccountModel.searchAccount(searchAccount)){
            txtActTypeSearch.setText(searchAccountModel.actType);
            txtBalanceSearch.setText(searchAccountModel.balance);
            txtDateSearch.setText(searchAccountModel.openDate);
            loandActId=txtActSearchId.getText();
            actIdForLoan=txtActSearchId.getText();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Account ID");
            alert.initOwner(Navigation.window);
            alert.show();
        }


    }

    public void nextOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        actId=txtActSearchId.getText();

        SearchAccount searchAccount=new SearchAccount(actId);
        SearchAccountModel searchAccountModel=new SearchAccountModel();
        if(searchAccountModel.searchAccount(searchAccount)){
            Navigation.navigate(Route.Gurdian, pane);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Account ID");
            alert.initOwner(Navigation.window);
            alert.show();
        }

    }


}
