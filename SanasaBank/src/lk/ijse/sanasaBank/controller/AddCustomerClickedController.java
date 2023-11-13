package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.model.AddMemberModel;
import lk.ijse.sanasaBank.to.AddMember;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCustomerClickedController implements Initializable {
    public TextField txtID;
    public TextField txtFistName;
    public TextField txtLastName;
    public TextField txtNic;
    public TextField txtContact;
    public TextArea txtAddress;
    public TextField txtAge;
    public JFXRadioButton rbMale;
    public JFXRadioButton rbFemail;
    public ToggleGroup group;
    public Label lblId;
    public Label lblFistName;
    public Label lblLastName;
    public Label lblContact;
    public Label lblNic;
    public Label lblAddress;
    public Label lblAge;
    @FXML
    private Label lblAdminName;

    @FXML
    private AnchorPane pane;

    String Id;
    String FirstName;
    String LastName;
    String Nic;
    String contact;
    String Address;
    int Age;


    @FXML
    void AddMemberOnAction(ActionEvent event) {

    }

    @FXML
    void accountsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
    }

    @FXML
    void homeClicked(MouseEvent event) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }

    @FXML
    void loansOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.IssueLoan, pane);
    }

    @FXML
    void memberOnAction(ActionEvent event) {

    }

    @FXML
    void paymentsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Payments, pane);
    }

    @FXML
    void removeMemberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.RemoveMember, pane);
    }

    @FXML
    void reportsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Reports, pane);
    }

    @FXML
    void searchMemberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.SearchedClicked, pane);
    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    @FXML
    void viewMemberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewMembers, pane);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            LogInFormController logInFormController=new LogInFormController();

        try {
            loadNextId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void AddtoDataBase(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        lblId.setText(null);
        lblFistName.setText(null);
        lblLastName.setText(null);
        lblNic.setText(null);
        lblContact.setText(null);
        lblAddress.setText(null);
        lblAge.setText(null);

        Pattern namePattern = Pattern.compile("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})");
        Matcher nameMatcher = namePattern.matcher(txtFistName.getText());
        boolean isNameMatches = nameMatcher.matches();

        Pattern idPattern=Pattern.compile("^[a-zA-Z0-9_.-]*$");
        Matcher IdMatcher = idPattern.matcher(txtID.getText());
        boolean isIdMatches = IdMatcher.matches();

        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher emailMatcher = emailPattern.matcher(txtLastName.getText());
        boolean isEmailMatches = emailMatcher.matches();
        
        Pattern nicPattern=Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        Matcher nicMatcher = nicPattern.matcher(txtNic.getText());
        boolean isNicMatches = nicMatcher.matches();
        
        Pattern contactPattern=Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        Matcher contactMatcher = contactPattern.matcher(txtContact.getText());
        boolean isContactMatches = contactMatcher.matches();

        Pattern agePattern = Pattern.compile("^\\S[0-9]{0,3}$");
        Matcher ageMatches = agePattern.matcher(txtAge.getText());
        boolean isAgeMatches = ageMatches.matches();

        if(isNameMatches){
               if(isEmailMatches){
                   if(isNicMatches){
                       if(isContactMatches){
                           if(isAgeMatches){
                               if(txtID.getText().isEmpty() || txtFistName.getText().isEmpty() || txtLastName.getText().isEmpty()||txtNic.getText().isEmpty()|| txtContact.getText().isEmpty()
                                       ||txtAddress.getText().isEmpty()||txtAge.getText().isEmpty()){
                                   lblId.setText("Make sure to fill this");
                                   lblFistName.setText("Make sure to fill this");
                                   lblLastName.setText("Make sure to fill this");
                                   lblNic.setText("Make sure to fill this");
                                   lblContact.setText("Make sure to fill this");
                                   lblAddress.setText("Make sure to fill this");
                                   lblAge.setText("Make sure to fill this");
                               }else{
                                   Id= txtID.getText();
                                   FirstName=txtFistName.getText();
                                   LastName=txtLastName.getText();
                                   Nic=txtNic.getText();
                                   contact=txtContact.getText();
                                   Address=txtAddress.getText();
                                   Age= Integer.parseInt(txtAge.getText());
                                   AddMember addMember=new AddMember(Id, FirstName,LastName,"Male",Address,Age, contact, Nic);
                                   AddMemberModel addMemberModel=new AddMemberModel();
                                   try{
                                       if(rbMale.isSelected()){
                                           if(addMemberModel.maleAdd(addMember)){
                                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Member Added Sucefully");
                                               alert.initOwner(Navigation.window);
                                               alert.show();

                                           }else{
                                               Alert alert = new Alert(Alert.AlertType.ERROR, "Member not Added ");
                                               alert.initOwner(Navigation.window);
                                               alert.show();
                                           }
                                       } else if (rbFemail.isSelected()) {
                                           if(addMemberModel.feMaleAdd(addMember)){
                                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Member Added Sucefully");
                                               alert.initOwner(Navigation.window);
                                               alert.show();
                                           }else {
                                               Alert alert = new Alert(Alert.AlertType.ERROR, "Member not Added ");
                                               alert.initOwner(Navigation.window);
                                               alert.show();
                                           }
                                       }
                                   }catch (SQLIntegrityConstraintViolationException q){
                                       lblId.setText("This ID is already exists");
                                   }
                               }
                           }else{
                               lblAge.setText("Invalid Age");
                           }
                       }else{
                           lblContact.setText("Invalid Contact");
                       }
                   }else{
                       lblNic.setText("Invalid Nic");
                   }
               }else{
                   lblLastName.setText("Invalid Email");
               }

        }else{
            lblId.setText("Invalid UserName");
        }

    }

    public void firstNameTypedOnAction(KeyEvent keyEvent) {

    }
    public  String genareteMectMatirialId() throws SQLException, ClassNotFoundException {
        String sql="SElect CusID FROM customer ORDER BY CusId DESC LIMIT 1";
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        String id = "";

        if(result.next()){
            id = result.getString(1);
        }

        String [] split = id.split("C0");
        int temp = Integer.parseInt(split[1]);
        temp+=1;
        return "C0"+temp;
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String id = genareteMectMatirialId();
        txtID.setText(id);
    }
}
