package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.DepositeMoney;
import lk.ijse.sanasaBank.to.WithDrawMoney;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WithdrawModel {
    public static String ownerName;
    public static String actType;
    public static String balance;
    public static String custID2;

    public static boolean setDetail(WithDrawMoney withDrawMoney) throws SQLException, ClassNotFoundException {


        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM account WHERE ActID = ?");
        //comers here
        pstm.setObject(1, withDrawMoney.getActId());

        ResultSet result = pstm.executeQuery();
        while (result.next()) {
            actType = result.getString(2);
            balance = result.getString(4);
            custID2 = result.getString(3);

            return true;
        }
        return false;

    }

    public static boolean setTransactionDetail(WithDrawMoney withDrawMoney) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO transaction VALUES (?,?,?,?,?,?)");
        pstm.setObject(1, withDrawMoney.getTransactionId());
        pstm.setObject(2, withDrawMoney.getActId());
        pstm.setObject(3, withDrawMoney.getWithdrawAmoubt());
        pstm.setObject(4, "WithDraw Cash");
        pstm.setObject(5, withDrawMoney.getWithdrawName());
        pstm.setObject(6, withDrawMoney.getDate());

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static boolean setAccountDetail(WithDrawMoney withDrawMoney) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("Update account set Balance=Balance-? where ActID=?;");
            pstm.setObject(1, withDrawMoney.getWithdrawAmoubt());
            pstm.setObject(2, withDrawMoney.getActId());
            //Update account set Balance=Balance+? where ActID=?
            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate > 0) {
                boolean isTransactionDetailSet = setTransactionDetail(withDrawMoney);
                if (isTransactionDetailSet) {

                    //DBConnection.getInstance().getConnection().commit();

                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

    public static void setActName() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE CusID = ?");
        pstm.setObject(1, custID2);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            ownerName = resultSet.getString(2);
        }
    }
}