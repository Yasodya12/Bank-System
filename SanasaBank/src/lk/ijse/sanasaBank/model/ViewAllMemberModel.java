package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.AddMember;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewAllMemberModel {

    public ArrayList<AddMember> viewAll() throws SQLException, ClassNotFoundException {
        ArrayList<AddMember> allMember=new ArrayList<>();
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer;");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){


            allMember.add(new AddMember(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    Integer.valueOf(resultSet.getString(6)),
                    resultSet.getString(7),
                    resultSet.getString(8)


            ));

        }
        return allMember;
    }
}
