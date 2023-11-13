package lk.ijse.sanasaBank.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;

public class SearchAccountController {
    public TextField txtSearchId;
    public TextField txtSearchName;
    @FXML
    private Label lblAdminName;

    @FXML
    private AnchorPane pane;

    @FXML
    void accountOnAction(ActionEvent event) {

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
    void searchAccountOnAction(ActionEvent event) {

    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    @FXML
    void viewAccountOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewAccount, pane);
    }

    public void searIdTypeOnAction(KeyEvent keyEvent) {
    }

    public void searNameTypeOnAction(KeyEvent keyEvent) {
    }
}
