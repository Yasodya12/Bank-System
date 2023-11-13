package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sanasaBank.model.LogInModel;
import lk.ijse.sanasaBank.to.logIn;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.RED;

public class LogInFormController implements Initializable {
    public Label lblPassword;
    public Label lblUserName;
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtPassword;

    public  String username;
    public void logInOnAction(ActionEvent actionEvent) {


        username=txtUserName.getText();
        String password=txtPassword.getText();

        logIn logIn=new logIn(username, password);
        LogInModel model= new LogInModel();
        try {
            boolean result=model.adminLogIn(logIn);
            if (result){

                System.out.println("Sign Up");
                Navigation.navigate(Route.AddCustomerFirst, pane);

            }else {

                txtUserName.requestFocus();
                txtUserName.setFocusColor(RED);
                txtPassword.setUnFocusColor(RED);
                lblUserName.setText("Invalid Username or Password");
                lblPassword.setText("Invalid Username or Password");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Sign Up");
        Navigation.navigate(Route.AdminSignUp, pane);
        /*Stage stage= (Stage) pane.getScene().getWindow();
        stage.setScene
                (new Scene((FXMLLoader.load(getClass().getResource("/lk/ijse/sanasaBank/view/AdminSignUp.fxml")))));*/

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
