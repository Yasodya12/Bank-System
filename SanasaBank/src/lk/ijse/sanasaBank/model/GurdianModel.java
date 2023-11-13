package lk.ijse.sanasaBank.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.Gurdian;
import lk.ijse.sanasaBank.to.OpenAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GurdianModel {


    public static ObservableList<String> gurdianName() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select name from  gurdian;");
        ResultSet result = pstm.executeQuery();

        ObservableList<String> gurdianName = FXCollections.observableArrayList();
        while (result.next()){
            gurdianName.add(result.getString(1));
        }

        return gurdianName;
    }
    public static ObservableList<String> gurdianId() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select GurdianID from  gurdian;");
        ResultSet result = pstm.executeQuery();

        ObservableList<String> gurdianId = FXCollections.observableArrayList();
        while (result.next()){
            gurdianId.add(result.getString(1));
        }
        return gurdianId;
    }
    public  String id;
    public  String name;
    public  double netWorth;
    public  String nic;
    public  String address;
    public  String age;

    public void setGurdianDetailName(Gurdian gurdian) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select * from gurdian WHERE name=?;");
        pstm.setObject(1, gurdian.getId());
        ResultSet result = pstm.executeQuery();
        //select * from gurdian WHERE GurdianName='Namal';

        if(result.next()){
            id =result.getString(1);
            name=result.getString(2);
            netWorth= Double.parseDouble(result.getString(3));
            nic=result.getString(4);
            address=result.getString(5);
            age=result.getString(6);

        }


    }
    public void setGurdianDetailId(Gurdian gurdian) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select * from gurdian WHERE GurdianID=?;");
        pstm.setObject(1, gurdian.getId());
        ResultSet result = pstm.executeQuery();
        //select * from gurdian WHERE GurdianName='Namal';

        if(result.next()){
            id =result.getString(1);
            name=result.getString(2);
            netWorth= Double.parseDouble(result.getString(3));
            nic=result.getString(4);
            address=result.getString(5);
            age=result.getString(6);

        }
    }
    public static boolean addGurdian(Gurdian gurdian) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO gurdian VALUES (?,?,?,?,?,?)");

        //INSERT INTO Account VALUES ('AC001','A001','C001','2000','2001.2.12');
        pstm.setObject(1, gurdian.getId());
        pstm.setObject(2, gurdian.getName());
        pstm.setObject(3, gurdian.getNetWorth());

        pstm.setObject(4, gurdian.getNic());
        pstm.setObject(5, gurdian.getAddress());
        pstm.setObject(6, gurdian.getAge());
        int executeUpdate = pstm.executeUpdate();
        if(executeUpdate>0){
            return true;
        }
    return false;
    }
}
