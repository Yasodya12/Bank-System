package lk.ijse.sanasaBank.controller;

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
import lk.ijse.sanasaBank.model.SearchAccountModel;
import lk.ijse.sanasaBank.model.SearchMemberModel;
import lk.ijse.sanasaBank.model.ViewAllAccountModel;
import lk.ijse.sanasaBank.model.ViewAllMemberModel;
import lk.ijse.sanasaBank.to.OpenAccount;
import lk.ijse.sanasaBank.to.SearchAccount;
import lk.ijse.sanasaBank.to.ViewAllAccount;
import lk.ijse.sanasaBank.to.ViewAllMember;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewAccountController implements Initializable {
    public TableView <ViewAllAccount>tblAccount;
    public TableColumn <ViewAllAccount, String>clmActId;
    public TableColumn <ViewAllAccount, String>clmActType;
    public TableColumn <ViewAllAccount, String>clmMemberId;
    public TableColumn <ViewAllAccount, Double>clmBalance;
    public TableColumn <ViewAllAccount, String>clmDate;
    public TextField txtActSearchId;
    public TextField txtActType;
    public TextField txtBalance;
    public TextField txtCostId;
    public TextField txtDate;
    public Label lblSearchId;
    @FXML
    private Label lblAdminName;

    @FXML
    private AnchorPane pane;

    String searchActId;

    @FXML
    void accountOnAction(ActionEvent event) {

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
    void openAccountOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
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
    void searchAccountOnAction(ActionEvent event) throws IOException {


    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    @FXML
    void viewAccountOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewAccount, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lblSearchId.setText(null);
        searchActId=txtActSearchId.getText();
        SearchAccount searchAccount=new SearchAccount(searchActId);
        SearchAccountModel searchAccountModel=new SearchAccountModel();
        boolean account = searchAccountModel.searchAccount(searchAccount);
        if(account){
            txtActType.setText(searchAccountModel.actType);
            txtBalance.setText(searchAccountModel.balance);
            txtDate.setText(searchAccountModel.openDate);
            txtCostId.setText(searchAccountModel.custId);
        }else{
            lblSearchId.setText("Invadil ID");
        }

    }

    public void actIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lblSearchId.setText(null);
        searchActId=txtActSearchId.getText();
        SearchAccount searchAccount=new SearchAccount(searchActId);
        SearchAccountModel searchAccountModel=new SearchAccountModel();
        boolean account = searchAccountModel.searchAccount(searchAccount);
        if(account){
            txtActType.setText(searchAccountModel.actType);
            txtBalance.setText(searchAccountModel.balance);
            txtDate.setText(searchAccountModel.openDate);
            txtCostId.setText(searchAccountModel.custId);
        }else{
            lblSearchId.setText("Invadil ID");
        }


    }
}
