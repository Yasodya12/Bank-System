package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.AddMember;
import lk.ijse.sanasaBank.to.OpenAccount;
import lk.ijse.sanasaBank.to.ViewAllAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewAllAccountModel {

    public ArrayList<OpenAccount> viewAllAccounts() throws SQLException, ClassNotFoundException, NullPointerException{
        ArrayList<OpenAccount> allAccount=new ArrayList<>();
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM account;");
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){

            allAccount.add(new OpenAccount(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Double.valueOf(resultSet.getString(4)),
                    resultSet.getString(5)
            ));

        }

        return allAccount;
    }

}
