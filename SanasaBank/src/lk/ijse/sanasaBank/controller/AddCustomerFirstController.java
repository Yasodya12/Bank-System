package lk.ijse.sanasaBank.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerFirstController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    public static Label lblAdminName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void addMemberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
    }

    @FXML
    void searchOrUpdateOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.SearchedClicked, pane);
    }

    @FXML
    void viewAllOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
    }

    public void memberOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
    }

    public void accountsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
    }

    public void loansOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.IssueLoan, pane);
    }

    public void paymentsOnAcion(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Payments, pane);
    }

    public void reportsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Reports, pane);
    }

    public void transactionOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    public void removeMemberOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.RemoveMember, pane);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }




}
