package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.logIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInModel {



        public boolean adminLogIn(logIn logIn) throws SQLException, ClassNotFoundException {
              PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM admin WHERE username = ?");
            //comers here
              pstm.setObject(1, logIn.getUsername());

                ResultSet result = pstm.executeQuery();
           //here not comes

                if(result.next() && result.getString(2).equals(logIn.getPassword())){

                    return true;
                }else {
                        return false;
                }


        }




}
