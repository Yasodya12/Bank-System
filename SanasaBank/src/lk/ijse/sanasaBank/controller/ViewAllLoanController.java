package lk.ijse.sanasaBank.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.model.ViewAllLoanModel;
import lk.ijse.sanasaBank.to.ViewAllLoan;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewAllLoanController implements Initializable {
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
    void issueLoanOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.IssueLoan, pane);
    }

    @FXML
    void loanOnAction(ActionEvent event) {

    }

    @FXML
    void memberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
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
    void viewAllOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewLoan, pane);
    }

    public void paymentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Payments, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            ArrayList<ViewAllLoan> viewAll = ViewAllLoanModel.viewAllLoans();
            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(viewAll);
            tblLoan.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //DELETE FROM customer WHERE CusID='C001';

    }
}
