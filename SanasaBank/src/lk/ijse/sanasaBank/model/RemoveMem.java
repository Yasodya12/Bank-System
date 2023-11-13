package lk.ijse.sanasaBank.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sanasaBank.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveMem {
    public static ObservableList<String> memId() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select CusID from  customer;");
        ResultSet result = pstm.executeQuery();

        ObservableList<String> memId = FXCollections.observableArrayList();
        while (result.next()){
            memId.add(result.getString(1));
        }
        return memId;
    }

    public static ObservableList<String> memName() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select firstName from  customer;");
        ResultSet result = pstm.executeQuery();

        ObservableList<String> memName = FXCollections.observableArrayList();
        while (result.next()){
            memName.add(result.getString(1));
        }

        return memName;
    }
}
