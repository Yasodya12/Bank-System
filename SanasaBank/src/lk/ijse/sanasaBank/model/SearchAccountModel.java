package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.SearchAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchAccountModel {
            public String actType;
            public String custId;

            public String balance;
            public String openDate;



        public  boolean searchAccount(SearchAccount searchAccount) throws SQLException, ClassNotFoundException {
            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM account WHERE ActID = ?");
            //comers here
            pstm.setObject(1, searchAccount.getSearchId());

            ResultSet result = pstm.executeQuery();

            if(result.next()){

                actType=result.getString(2);
                custId=result.getString(3);
                balance=result.getString(4);
                openDate=result.getString(5);

                return true;
            }
            return false;
        }

}
