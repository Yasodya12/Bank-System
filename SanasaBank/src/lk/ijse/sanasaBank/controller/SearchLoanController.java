package lk.ijse.sanasaBank.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;

public class SearchLoanController {
    @FXML
    private AnchorPane pane;

    @FXML
    void accountOnAction(ActionEvent event) throws IOException {
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
    void viewAllOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewLoan, pane);
    }
}
