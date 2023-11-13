package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.model.SearchMemberModel;
import lk.ijse.sanasaBank.to.UpdateMember;
import lk.ijse.sanasaBank.to.searchMember;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchedClickedController implements Initializable {
    public Label lblAdminName;
    public ImageView homeOnAction;
    public TextField txtSearchId;
    public TextField txtFistName;
    public TextField txtLastName;
    public TextField txtNic;
    public TextField txtContact;
    public TextArea txtAddress;
    public TextField txtAge;
    public JFXRadioButton rbMale;
    public TextField txtSearchName;
    public JFXRadioButton rbFemail;
    public ToggleGroup group2;
    public JFXButton btnSearch;
    public JFXButton btnUpdate;
    public Label lblFullName;
    public Label lblEmail;
    public Label lblNic;
    public Label lblContact;
    public Label lblAge;
    public Label lblAddress;
    @FXML
    private AnchorPane pane;

    String id;
    String name;

    String updateFirstName;
    String updateLastName;
    String nic;
    String contact;
    String address;
    int age;


    @FXML
    void memberOnAction(ActionEvent event) throws IOException {
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
    void addMemberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
    }

    @FXML
    void searchEdOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.SearchedClicked, pane);
    }

    @FXML
    void removeOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.RemoveMember, pane);
    }

    @FXML
    void viewAllOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewMembers, pane);
    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }

    public void firstNameTypedOnAction(KeyEvent keyEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtFistName.getText().equals("")){
           lblFullName.setText("Invalid Name");
        }else if (txtLastName.getText().equals("")){
            lblEmail.setText("Invalid Email");
        }else if(txtNic.getText().equals("")){
                lblNic.setText("Invalid Email");
        } else if (txtContact.getText().equals("")) {
            lblContact.setText("Invalid Contact");
        } else if (txtAddress.getText().equals("")) {
            lblAddress.setText("Make Sure to Fill This");
        } else if (txtAge.equals("")) {
            lblAge.setText("Invalid Age");
        }else{
            Pattern namePattern = Pattern.compile("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})");
            Matcher nameMatcher = namePattern.matcher(txtFistName.getText());
            boolean isNameMatches = nameMatcher.matches();
            if(isNameMatches){
                Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
                Matcher emailMatcher = emailPattern.matcher(txtLastName.getText());
                boolean isEmailMatches = emailMatcher.matches();
                if(isEmailMatches){
                    Pattern nicPattern=Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
                    Matcher nicMatcher = nicPattern.matcher(txtNic.getText());
                    boolean isNicMatches = nicMatcher.matches();
                    if(isNicMatches){
                        Pattern contactPattern=Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
                        Matcher contactMatcher = contactPattern.matcher(txtContact.getText());
                        boolean isContactMatches = contactMatcher.matches();
                        if(isContactMatches){
                            Pattern agePattern = Pattern.compile("^\\S[0-9]{0,3}$");
                            Matcher ageMatches = agePattern.matcher(txtAge.getText());
                            boolean isAgeMatches = ageMatches.matches();
                            if(isAgeMatches){
                                updateFirstName=txtFistName.getText();
                                updateLastName=txtLastName.getText();
                                nic=txtNic.getText();
                                contact=txtContact.getText();
                                address=txtAddress.getText();
                                age= Integer.parseInt(txtAge.getText());
                                id=txtSearchId.getText();

                                UpdateMember updateMember=new UpdateMember(id,updateFirstName, updateLastName, nic, contact, address, age);



                                SearchMemberModel searchMemberModel=new SearchMemberModel();
                                if(searchMemberModel.update(updateMember)){
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Member Updated");
                                    alert.initOwner(Navigation.window);
                                    alert.show();
                                    btnUpdate.setDisable(false);
                                    lblAge.setText(null);
                                    lblAddress.setText(null);
                                    lblFullName.setText(null);
                                    lblEmail.setText(null);
                                    lblNic.setText(null);
                                    lblContact.setText(null);
                                }else{
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong");
                                    alert.initOwner(Navigation.window);
                                    alert.show();
                                    btnUpdate.setDisable(false);
                                    lblAge.setText(null);
                                    lblAddress.setText(null);
                                    lblFullName.setText(null);
                                    lblEmail.setText(null);
                                    lblNic.setText(null);
                                    lblContact.setText(null);
                                }
                            }else{
                                lblAge.setText("Invalid Age");
                            }
                        }else{
                            lblContact.setText("Invalid Contact");
                        }
                    }else{
                     lblNic.setText("Invalid NIC");
                    }
                }else{
                    lblEmail.setText("Invalid Email");
                }
            }else{
                lblFullName.setText("Invalid Name");
            }
        }
    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lblAge.setText(null);
        lblAddress.setText(null);
        lblFullName.setText(null);
        lblEmail.setText(null);
        lblNic.setText(null);
        lblContact.setText(null);
        id=txtSearchId.getText();
        name=txtSearchName.getText();
        txtFistName.setText(null);
        txtLastName.setText(null);
        txtNic.setText(null);
        txtContact.setText(null);
        txtAddress.setText(null);
        txtAge.setText(null);

       searchMember searchMember=new searchMember(id, name);
        if(txtSearchId.getText().isEmpty() && txtSearchName.getText().isEmpty()){
            btnSearch.setDisable(true);
        }else{
            btnSearch.setDisable(false);
        }

        SearchMemberModel searchMemberModel=new SearchMemberModel();

        if(searchMemberModel.search(searchMember)){
                txtFistName.setText(searchMemberModel.firstNameResult);
                txtLastName.setText(searchMemberModel.lastNameResult);
                txtNic.setText(searchMemberModel.nicResult);
                txtContact.setText(searchMemberModel.contactResult);
                txtAddress.setText(searchMemberModel.addressResult);
                txtAge.setText(searchMemberModel.ageResult);
                btnUpdate.setDisable(false);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username Or ID may be same Username must be duplicated make sure to search with ID");
            alert.initOwner(Navigation.window);
            alert.show();
        }
    }
    public void searNameTypeOnAction(KeyEvent keyEvent) {

        System.out.println("gats");
    }

    public void searIdTypeOnAction(KeyEvent keyEvent) {


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.setDisable(true);
        btnSearch.setDisable(true);
    }


    public void MemIdSearchOnAction(KeyEvent keyEvent) {
        if(txtSearchId.getText().isEmpty()){
            btnSearch.setDisable(true);
        }else{
            btnSearch.setDisable(false);
        }
    }

    public void MemNameSearchOnAction(KeyEvent keyEvent) {
        if(txtSearchName.getText().isEmpty()){
            btnSearch.setDisable(true);
        }else{
            btnSearch.setDisable(false);
        }
    }
}