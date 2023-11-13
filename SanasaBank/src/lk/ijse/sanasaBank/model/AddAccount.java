package lk.ijse.sanasaBank.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.AddMember;
import lk.ijse.sanasaBank.to.OpenAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddAccount {

    public String memId;
    public String memName;
    public String memNic;
    public ObservableList<String> actType() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select name from  ActType;");
        ResultSet result = pstm.executeQuery();

        ObservableList<String> actTypeId =FXCollections.observableArrayList();
        while (result.next()){
            actTypeId.add(result.getString(1));
        }
        return actTypeId;
    }

    public boolean setDetail(OpenAccount openAccount) throws SQLException, ClassNotFoundException {

        if(openAccount.getSearchName()==null){
            System.out.println("Athulata awa");
            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE CusID = ?");
            //comers here
            pstm.setObject(1, openAccount.getSearchId());

            ResultSet result = pstm.executeQuery();

            if(result.next()){

                memId= result.getString(1);
                memName=result.getString(2);
                memNic=result.getString(8);


                return true;
            }





        }else{
            System.out.println("Athulata awa2");
            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE firstName = ?");
            //comers here
            pstm.setObject(1, openAccount.getSearchName());

            ResultSet result = pstm.executeQuery();


            if(result.next()){

                memId= result.getString(1);
                memName=result.getString(2);
                memNic=result.getString(8);

                return true;
            }

        }
        return false;
    }

    public boolean addAccount(OpenAccount openAccount) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Account VALUES (?,?,?,?,?)");

        //INSERT INTO Account VALUES ('AC001','A001','C001','2000','2001.2.12');
        pstm.setObject(1, openAccount.getActId());
        pstm.setObject(2, openAccount.getActTypeId());
        pstm.setObject(3, openAccount.getCustId());

        pstm.setObject(4, openAccount.getBalance());

        pstm.setObject(5, openAccount.getDate());


        int executeUpdate = pstm.executeUpdate();

        if(executeUpdate>0){

            return true;
        }
        return false;
    }

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
