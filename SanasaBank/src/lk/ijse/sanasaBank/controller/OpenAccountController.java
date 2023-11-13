package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.model.AddAccount;
import lk.ijse.sanasaBank.model.ViewAllMemberModel;
import lk.ijse.sanasaBank.to.AddMember;
import lk.ijse.sanasaBank.to.OpenAccount;
import lk.ijse.sanasaBank.to.ViewAllMember;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenAccountController implements Initializable {
    public TextField txtSearchId;
    public TextField txtSearchName;
    public Label lblId;
    public Label lblMemName;
    public Label lblAddMemId;
    public Label lblMemId;
    public Label lblMemName2;
    public Label lblMemNic;
    public Label lblActTypeLable;
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


    public TextField txtActId;

    public TextField txtGuardianName;
    public TextField txtDate;
    public TextField txtDepositeMoney;
    public TextField txtMoneyName;
    public JFXComboBox cmbActType;
    public JFXButton btnAct;
    @FXML
    private Label lblAdminName;

    @FXML
    private AnchorPane pane;

    String searchId;
    String searchName;
    String actId;
    String actType;

    String memId;
    Double despositeMoney;
    String date;



    @FXML
    void accountOnAction(ActionEvent event) {

    }

    @FXML
    void loanOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.IssueLoan, pane);
    }

    @FXML
    void memberOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.MemberOrAddMember, pane);
    }

    @FXML
    void openAccountOnAction(ActionEvent event) {

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
    void searchAccountOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewAccount, pane);
    }

    @FXML
    void transactionOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    @FXML
    void viewAcountonAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewAccount, pane);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }

    public void btnCreateAct(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        lblActTypeLable.setText(null);
        lblAddMemId.setText(null);
            if(lblMemId.getText()!=""){
                try {


                    if(cmbActType.getValue()!=null){
                        if(txtDepositeMoney.getText()!=null){
                            //txtDepositeMoney.getText()!=null
                            Pattern idPattern=Pattern.compile("^[1-9]\\d{3,}");
                            Matcher IdMatcher = idPattern.matcher(txtDepositeMoney.getText());
                            boolean isIdMatches = IdMatcher.matches();
                            if(isIdMatches){
                                System.out.println("Ow Awa");
                                actId=txtActId.getText();
                                actType= (String) cmbActType.getValue();
                                memId=lblMemId.getText();
                                despositeMoney= Double.valueOf(txtDepositeMoney.getText());
                                date=txtDate.getText();
                                OpenAccount openAccount=new OpenAccount(actId, actType, memId, despositeMoney, date);
                                AddAccount addAccount1=new AddAccount();


                                if(addAccount1.addAccount(openAccount)){
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Account Added Sucefully");
                                    alert.initOwner(Navigation.window);
                                    alert.show();

                                }else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Account Not Added");
                                    alert.initOwner(Navigation.window);
                                    alert.show();
                                }
                            }else{

                                lblAddMemId.setText("Invail Amount");
                                //lblActTypeLable.setText("Plese Select Act Type");
                            }
                        }else{
                            lblAddMemId.setText("Plese Enter Monet");
                        }
                    }else{
                        lblActTypeLable.setText("Plese Select Act Type");
                    }
                }catch (SQLIntegrityConstraintViolationException q){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Account ID Already Exists");
                    alert.initOwner(Navigation.window);
                    alert.show();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Plese Search The member Before you creating the account ");
                alert.initOwner(Navigation.window);
                alert.show();
            }




    }
    AddAccount addAccount=new AddAccount();
    public void showDate(){
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
        String date=s.format(d);
        txtDate.setText(date);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDate();
        try {
            loadNextId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        txtSearchId.setText(null);
        txtSearchName.setText(null);
        try {
            ObservableList<String> actTypeId = addAccount.actType();
            cmbActType.setItems(actTypeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public void EnterOnAction(InputMethodEvent inputMethodEvent) throws SQLException, ClassNotFoundException {

    }

    public void EnterOnAction2(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtSearchName.setText(null);
        lblId.setText(null);
        lblMemName.setText(null);
        lblMemId.setText(null);
        lblMemName2.setText(null);
        lblMemNic.setText(null);


        txtDepositeMoney.setText(null);
        txtMoneyName.setText(null);

        searchId=txtSearchId.getText();
        searchName=txtSearchName.getText();

        OpenAccount openAccount=new OpenAccount(searchId, searchName);
        AddAccount addAccount1=new AddAccount();
        if(addAccount1.setDetail(openAccount)){
            lblMemId.setText(addAccount1.memId);
            lblMemName2.setText(addAccount1.memName);
            lblMemNic.setText(addAccount1.memNic);
        }else {
           lblId.setText("Invalid ID");
        }
    }

    public void enterOnActionName(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtSearchId.setText(null);
        lblId.setText(null);
        lblMemName.setText(null);
        lblMemId.setText(null);
        lblMemName2.setText(null);
        lblMemNic.setText(null);
        txtDepositeMoney.setText(null);
        txtMoneyName.setText(null);
        searchId=txtSearchId.getText();
        searchName=txtSearchName.getText();
        OpenAccount openAccount=new OpenAccount(searchId, searchName);
        AddAccount addAccount1=new AddAccount();
        if(addAccount1.setDetail(openAccount)){
            lblMemId.setText(addAccount1.memId);
            lblMemName2.setText(addAccount1.memName);
            lblMemNic.setText(addAccount1.memNic);

        }else{
            lblMemName.setText("Invalid Name");
        }
    }
    public  String genareteMectMatirialId() throws SQLException, ClassNotFoundException {
        String sql="SELECT ActID FROM account ORDER BY ActID DESC LIMIT 1";
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        String id = "";

        if(result.next()){
            id = result.getString(1);
        }

        String [] split = id.split("A0");
        int temp = Integer.parseInt(split[1]);
        temp+=1;
        return "A0"+temp;
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String id = genareteMectMatirialId();
        txtActId.setText(id);
    }
}
