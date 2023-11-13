package lk.ijse.sanasaBank.util;

/*
    @author Rishika
    @created 11/1/22 - 1:27 PM   
*/

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sanasaBank.controller.AdminSignUpController;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    public static Stage window;
    public static void navigate(Route route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case AdminSignUp:
                window.setTitle("Admin Sign Up");
                initUI("AdminSignUp.fxml");
                break;
            case  AddCustomerFirst:
                window.setTitle("Sanasa Bank Managment");
                initUI("AddCustomerFirst.fxml");
                break;
            case SignUpForm:
                window.setTitle("Sanasa Bank Managment Sign Up");
                initUI("AdminSignUp.fxml");
                break;
            case MemberOrAddMember:
                window.setTitle("Sanasa Bank Managment Add Member");
                initUI("AddCustomerClicked.fxml");
                break;
            case SearchedClicked:
                window.setTitle("Sanasa Bank Managment Search Member");
                initUI("SearchedClicked.fxml");
                break;
            case RemoveMember:
                window.setTitle("Sanasa Bank Managment Remove Member");
                initUI("RemoveClicked.fxml");
                break;
            case ViewMembers:
                window.setTitle("Sanasa Bank Managment View Member");
                initUI("ViewAllClicked.fxml");
             break;
            case Home:
                window.setTitle("Sanasa Bank Managment");
                initUI("LogInForm.fxml");
            break;
            case OpenAccount:
                window.setTitle("Sanasa Bank Managment");
                initUI("OpenAccount.fxml");
                break;
            case SearchAccount:
                window.setTitle("Sanasa Bank Managment");
                initUI("SearchAccount.fxml");
                break;
            case ViewAccount:
                window.setTitle("Sanasa Bank Managment");
                initUI("ViewAccount.fxml");
                break;
            case IssueLoan:
                window.setTitle("Sanasa Bank Managment");
                initUI("IssueLoan.fxml");
                break;
            case SearchLoan:
                window.setTitle("Sanasa Bank Managment");
                initUI("SearchLoan.fxml");
                break;
            case ViewLoan:
                window.setTitle("Sanasa Bank Managment");
                initUI("ViewAllLoan.fxml");
                break;
            case Payments:
                window.setTitle("Sanasa Bank Payments");
                initUI("Payments.fxml");
                break;
            case Reports:
                window.setTitle("Sanasa Bank Reports");
                initUI("Reports.fxml");
                break;
            case DepositeMoney:
                window.setTitle("Sanasa Bank Deposite");
                initUI("DepositeMoney.fxml");
                break;
            case WithDraw:
                window.setTitle("Sanasa Bank Deposite");
                initUI("WithDrawMoney.fxml");
                break;
            case IssueLoanII:
                window.setTitle("Sanasa Bank Deposite");
                initUI("IssueLoanII.fxml");
                break;
            case Gurdian:
                window.setTitle("Sanasa Bank Deposite");
                initUI("GurdianLoan.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                        .getResource("/lk/ijse/sanasaBank/view/" + location)));
    }
}
