package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.AddMember;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMemberModel {
    public boolean maleAdd(AddMember member) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?)");
        pstm.setObject(1, member.getCustId());
        pstm.setObject(2, member.getName());
        pstm.setObject(3, member.getEmail());
        pstm.setObject(4, "Male");
        pstm.setObject(5, member.getAddress());
        pstm.setObject(6, Integer.toString(member.getAge()));
        pstm.setObject(7, member.getContact());
        pstm.setObject(8, member.getNic());
        int executeUpdate = pstm.executeUpdate();
        System.out.println(executeUpdate);
        if (executeUpdate > 0) {

            return true;
        }
        return false;
    }

    public boolean feMaleAdd(AddMember member) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?)");
        pstm.setObject(1, member.getCustId());
        pstm.setObject(2, member.getName());
        pstm.setObject(3, member.getEmail());
        pstm.setObject(4, "Female");
        pstm.setObject(5, member.getAddress());
        pstm.setObject(6, Integer.toString(member.getAge()));
        pstm.setObject(7, member.getContact());
        pstm.setObject(8, member.getNic());
        int executeUpdate = pstm.executeUpdate();

        if (executeUpdate > 0) {

            return true;
        }
        return false;
    }
}
