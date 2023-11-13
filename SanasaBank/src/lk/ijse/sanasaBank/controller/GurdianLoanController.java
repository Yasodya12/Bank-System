package lk.ijse.sanasaBank.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.model.GurdianModel;
import lk.ijse.sanasaBank.to.Gurdian;
import lk.ijse.sanasaBank.util.Navigation;
import lk.ijse.sanasaBank.util.Route;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.ijse.sanasaBank.controller.AddCustomerFirstController.lblAdminName;

public class GurdianLoanController implements Initializable {
    public Label lblId;
    public Label lblName;
    public Label lblNic;
    public Label lblAddress;
    public Label lblAge;
    public Label lblNetWorth;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnAddGurdian;

    @FXML
    private JFXComboBox<String> cmbGurdianId;

    @FXML
    private JFXComboBox<String> cmbGurdianName;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtGurdianAge;

    @FXML
    private TextField txtGurdianId;

    @FXML
    private TextField txtGurdianName;

    @FXML
    private TextField txtGurdianNic;

    @FXML
    private TextField txtNetWorth;

    @FXML
    void accountOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.OpenAccount, pane);
    }

    @FXML
    public static String gurdianId;
    @FXML
    void addOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        lblName.setText(null);
        lblId.setText(null);
        lblAddress.setText(null);
        lblAge.setText(null);
        lblNetWorth.setText(null);
        lblNic.setText(null);



        ObservableList<String> gurdianId1 = GurdianModel.gurdianId();
            boolean isHave=false;
            for(String id:gurdianId1){
                if(id.equals(txtGurdianId.getText())){
                    isHave=true;
                }
            }
            if(isHave) {
                Navigation.navigate(Route.IssueLoanII, pane);
                gurdianId = txtGurdianId.getText();
            }  else {
                if(txtGurdianId.getText().equals("")||txtNetWorth.getText().equals("")||txtGurdianName.getText().equals("")||txtGurdianAge.getText().equals("")
                ||txtGurdianNic.getText().equals("")||txtAddress.getText().equals("")){
                    lblName.setText("Make Sure to fill this");
                    lblAddress.setText("Make Sure to fill this");
                    lblNetWorth.setText("Make Sure to fill this");
                    lblNic.setText("Make Sure to fill this");
                    lblAge.setText("Make Sure to fill this");
                }else{

                    Pattern namePattern = Pattern.compile("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})");
                    Matcher nameMatcher = namePattern.matcher(txtGurdianName.getText());
                    boolean isNameMatches = nameMatcher.matches();

                    Pattern nicPattern=Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
                    Matcher nicMatcher = nicPattern.matcher(txtGurdianNic.getText());
                    boolean isNicMatches = nicMatcher.matches();

                    Pattern agePattern = Pattern.compile("^\\S[0-9]{0,3}$");
                    Matcher ageMatches = agePattern.matcher(txtGurdianAge.getText());
                    boolean isAgeMatches = ageMatches.matches();

                    Pattern netWorthPattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");
                    Matcher netWorthMatches = netWorthPattern.matcher(txtNetWorth.getText());
                    boolean isNetWorthMatches = netWorthMatches.matches();

                    if(isNameMatches){
                        if(isNicMatches){
                            if(isAgeMatches){
                                    if(isNetWorthMatches){
                                        String id = txtGurdianId.getText();
                                        String name = txtGurdianName.getText();
                                        double netWorth = Double.parseDouble(txtNetWorth.getText());
                                        String nic = txtGurdianNic.getText();
                                        String address = txtAddress.getText();
                                        String age = txtGurdianAge.getText();
                                        Gurdian gurdian = new Gurdian(id, name, netWorth, nic, address, age);
                                        boolean b = GurdianModel.addGurdian(gurdian);

                                        if (b) {
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Guarantor is Added");
                                            alert.initOwner(Navigation.window);
                                            alert.show();
                                            Navigation.navigate(Route.IssueLoanII, pane);
                                            gurdianId = txtGurdianId.getText();
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR, "Guarantor not Added Something went wrong(Check Guarantor ID)");
                                            alert.initOwner(Navigation.window);
                                            alert.show();
                                        }
                                    }else{
                                        lblNetWorth.setText("Invalid Data");
                                    }
                            }else{
                                txtGurdianAge.setText("Invalid Age");
                            }
                        }else{
                            lblNic.setText("Invalid NIC num");
                        }
                    }else{
                        lblName.setText("Invalid UserName");
                    }

                }
                //Navigation.navigate(Route.IssueLoanII, pane);
            }


    }

    @FXML
    void homeOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.Home, pane);
    }

    @FXML
    void idOnAction(ActionEvent event) {

    }

    @FXML
    void issueLoanOnAction(ActionEvent event) {

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
    void viewAllLoanOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.ViewLoan, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            loadNextId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            ObservableList<String> gurdianName = GurdianModel.gurdianName();
            cmbGurdianName.setItems(gurdianName);
            ObservableList<String> gurdianId = GurdianModel.gurdianId();
            cmbGurdianId.setItems(gurdianId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void transactionOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.DepositeMoney, pane);
    }

    public void nameOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

    }

    public void selectIdOnAtion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

       /* txtGurdianId.setText(null);
        txtGurdianName.setText(null);
        txtGurdianNic.setText(null);
        txtGurdianAge.setText(null);
        txtAddress.setText(null);
        txtNetWorth.setText(String.valueOf(null));*/

        String nameOrID=cmbGurdianId.getValue();
        Gurdian gurdian=new Gurdian(nameOrID);
        GurdianModel gurdianModel=new GurdianModel();
        gurdianModel.setGurdianDetailId(gurdian);
        txtGurdianId.setText(gurdianModel.id);
        txtGurdianName.setText(gurdianModel.name);
        txtGurdianNic.setText(gurdianModel.nic);
        txtGurdianAge.setText(gurdianModel.age);
        txtAddress.setText(gurdianModel.address);
        txtNetWorth.setText(String.valueOf(gurdianModel.netWorth));
        cmbGurdianName.setValue(null);
        btnAddGurdian.setDisable(false);

    }

    public void selectNameOnAtion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nameOrID=cmbGurdianName.getValue();
        Gurdian gurdian=new Gurdian(nameOrID);
        GurdianModel gurdianModel=new GurdianModel();
        gurdianModel.setGurdianDetailName(gurdian);
        txtGurdianId.setText(gurdianModel.id);
        txtGurdianName.setText(gurdianModel.name);
        txtGurdianNic.setText(gurdianModel.nic);
        txtGurdianAge.setText(gurdianModel.age);
        txtAddress.setText(gurdianModel.address);
        txtNetWorth.setText(String.valueOf(gurdianModel.netWorth));
        cmbGurdianId.setValue(null);
        btnAddGurdian.setDisable(false);
    }


    public void idTypeOnAction(KeyEvent keyEvent) {
        txtGurdianName.setText(null);
        txtAddress.setText(null);
        txtGurdianNic.setText(null);
        txtGurdianAge.setText(null);
        txtNetWorth.setText(null);
        cmbGurdianId.setValue(null);
        cmbGurdianName.setValue(null);
    }

    public  String genareteMectMatirialId() throws SQLException, ClassNotFoundException {
        String sql="SElect GurdianID FROM gurdian ORDER BY GurdianID DESC LIMIT 1";
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        String id = "";

        if(result.next()){
            id = result.getString(1);
        }

        String [] split = id.split("G0");
        int temp = Integer.parseInt(split[1]);
        temp+=1;
        return "G0"+temp;
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String id = genareteMectMatirialId();
        txtGurdianId.setText(id);
    }
}
