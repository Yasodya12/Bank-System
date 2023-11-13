package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sanasaBank.model.AdminSignUpModel;
import lk.ijse.sanasaBank.to.signUp;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.paint.Color.*;

public class AdminSignUpController {
    public Label lblAdminCode;
    public Label lblPassword;
    public Label lblUserName;
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtAdminCode;

    @FXML
    private JFXTextField txtPassword;

    private Matcher userNameMatcher;
    private Matcher passwordMatcher;


    @FXML

    void signUpOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String username= txtUserName.getText();
        String password= txtPassword.getText();
        String adminCode= txtAdminCode.getText();

        signUp sUp=new signUp(username,password,adminCode);
            try{
                AdminSignUpModel adminSignUpModel=new AdminSignUpModel();
                boolean b=adminSignUpModel.signUp(sUp);
                if(b){
                    Navigation.navigate(Route.AddCustomerFirst, pane);
                    System.out.println("Hari");


                }else{
                    lblAdminCode.setText("Invalid Admin Code");
                }
            }catch (SQLIntegrityConstraintViolationException | IOException e){

                Alert alert = new Alert(Alert.AlertType.ERROR, "Username is already exists");
                alert.show();
            }




    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {

        Stage stage= (Stage) pane.getScene().getWindow();
        stage.setScene
                (new Scene((FXMLLoader.load(getClass().getResource("/lk/ijse/sanasaBank/view/LogInForm.fxml")))));
    }

    /*public void userNameKeyReleseOnAction(KeyEvent keyEvent) {
        if(!userNameMatcher.matches()) {
            txtUserName.setFocusColor(RED);
            lblUserName.setText("Username must be a-z and 0-9");
        }else {
            txtUserName.setFocusColor(BLACK);
            lblUserName.setText(null);
        }
    }*/





    public void userNameKeyTypedOnAction(KeyEvent keyEvent) {
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());
        if(!userNameMatcher.matches()) {
            txtUserName.setFocusColor(RED);
            lblUserName.setText("Username must be a-z and 0-9");
        }else {
            txtUserName.setFocusColor(BLACK);
            lblUserName.setText(null);
        }
    }

    public void passwordKeytypedOnAction(KeyEvent keyEvent) {
        Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        passwordMatcher = passwordPattern.matcher(txtPassword.getText());
        if(!passwordMatcher.matches()){
            txtPassword.setFocusColor(RED);
            lblPassword.setText("Password must be a-z and 0-9");
        }else {
            txtPassword.setFocusColor(BLACK);
            lblPassword.setText(null);
        }
    }
}
