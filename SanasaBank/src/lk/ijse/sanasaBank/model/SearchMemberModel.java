package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.controller.SearchedClickedController;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.UpdateMember;
import lk.ijse.sanasaBank.to.searchMember;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchMemberModel {
    public String firstNameResult;
    public String lastNameResult;

    public String addressResult;

    public String genderResult;

    public String ageResult;

    public String contactResult;

    public String nicResult;
    public boolean search(searchMember searchMember) throws SQLException, ClassNotFoundException {

        if(searchMember.getName().equals("")){
            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE CusID = ?");
            //comers here
            pstm.setObject(1, searchMember.getId());

            ResultSet result = pstm.executeQuery();

            if(result.next()){

                firstNameResult= result.getString(2);
                lastNameResult=result.getString(3);
                genderResult=result.getString(4);
                addressResult=result.getString(5);
                ageResult=result.getString(6);
                contactResult=result.getString(7);
                nicResult=result.getString(8);

                return true;
            }





        }else{
            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE firstName = ?");
            //comers here
            pstm.setObject(1, searchMember.getName());

            ResultSet result = pstm.executeQuery();


            if(result.next()){

                firstNameResult= result.getString(2);
                lastNameResult=result.getString(3);
                genderResult=result.getString(4);
                addressResult=result.getString(5);
                ageResult=result.getString(6);
                contactResult=result.getString(7);
                nicResult=result.getString(8);

                return true;
            }

            }
        return false;
    }

    public boolean update(UpdateMember updateMember) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("update customer set firstName=?, email=?, address=?, age=?, contact=?,nic=? where CusID=?;");
        pstm.setObject(1, updateMember.getFirstName());
        pstm.setObject(2, updateMember.getLastName());
        pstm.setObject(3, updateMember.getAddress());
        pstm.setObject(4, Integer.toString(updateMember.getAge()));
        pstm.setObject(5, updateMember.getContact());
        pstm.setObject(6, updateMember.getNic());
        pstm.setObject(7, updateMember.getId());
        //update customer set firstName='kusum', lastName='Nimal', address='Matara', age='23', contact='43',nic='assd' where CusID='C005';
        int executeUpdate = pstm.executeUpdate();

        if(executeUpdate>0){
            return true;
        }
        return false;
    }
}
