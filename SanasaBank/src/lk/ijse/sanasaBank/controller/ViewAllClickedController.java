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
import lk.ijse.sanasaBank.model.ViewAllMemberModel;
import lk.ijse.sanasaBank.to.AddMember;
import lk.ijse.sanasaBank.to.ViewAllMember;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewAllClickedController  implements Initializable {

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

    @FXML
    void membersOnAction(ActionEvent event) throws IOException {
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
    void SearchmembersOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.SearchedClicked, pane);
    }

    @FXML
    void removemembersOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.RemoveMember, pane);
    }

    @FXML
    void viewmembersOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewMembers, pane);
    }

    @FXML
    void transactionMembersOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    public void addMembersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);

    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Home, pane);
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

    }
}
