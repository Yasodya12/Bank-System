package lk.ijse.sanasaBank.model;

import javafx.scene.control.Alert;
import lk.ijse.sanasaBank.controller.AdminSignUpController;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.signUp;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminSignUpModel{
    public boolean signUp(signUp sgn) throws SQLException, ClassNotFoundException {
        if(sgn.getAdminCode().equals("sanasa")) {

            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO admin VALUES (?,?)");
            pstm.setObject(1, sgn.getUsername());
            pstm.setObject(2, sgn.getPassword());

            int executeUpdate = pstm.executeUpdate();
            System.out.println(executeUpdate);
            if (executeUpdate > 0) {
                System.out.println("Hari pakor");
                return true;
            }
        }
        return false;
    }
}
