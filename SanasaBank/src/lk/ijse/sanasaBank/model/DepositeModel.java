package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.DepositeMoney;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DepositeModel {
    String ownerName;
    public String actType;

    public static String custId;
    public boolean setDetail(DepositeMoney depositeMoney) throws SQLException, ClassNotFoundException {

        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM account WHERE ActID = ?");
        //comers here
        pstm.setObject(1, depositeMoney.getActId());


        ResultSet result = pstm.executeQuery();
        while (result.next()){
            actType=result.getString(2);
            custId=result.getString(3);
            return true;
        }
        return false;

    }

    public static boolean setTransactionDetail(DepositeMoney depositeMoney) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO transaction VALUES (?,?,?,?,?,?)");
        pstm.setObject(1, depositeMoney.getTransactionId());
        pstm.setObject(2, depositeMoney.getActId());
        pstm.setObject(3, depositeMoney.getDepositeAmoubt());
        pstm.setObject(4, depositeMoney.getTransType());
        pstm.setObject(5, depositeMoney.getDepositeName());
        pstm.setObject(6, depositeMoney.getDate());

        int executeUpdate = pstm.executeUpdate();
        if(executeUpdate>0){
            return true;
        }
        return false;
    }

    public static boolean setAccountDetail(DepositeMoney depositeMoney) throws SQLException, ClassNotFoundException, InvocationTargetException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("Update account set Balance=Balance+? where ActID=?;");
            pstm.setObject(1,Double.valueOf(depositeMoney.getDepositeAmoubt()));
            pstm.setObject(2,depositeMoney.getActId());
            //Update account set Balance=100 where ActID='A001';
            int executeUpdate = pstm.executeUpdate();
            if(executeUpdate>0){
                boolean isTransactionDetailSet = setTransactionDetail(depositeMoney);
                if(isTransactionDetailSet){

                   // DBConnection.getInstance().getConnection().commit();

                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }
    public static boolean chequeEnter(DepositeMoney depositeMoney) throws SQLException, ClassNotFoundException {
        try {
        DBConnection.getInstance().getConnection().setAutoCommit(false);
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO cheque (purpose, amount, date ) VALUES(?,?,?) ");
        pstm.setObject(1, "Deposite");
        pstm.setObject(2, Double.valueOf(depositeMoney.getDepositeAmoubt()));
        pstm.setObject(3, depositeMoney.getDate());
        //Update account set Balance=Balance+? where ActID=?
        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate > 0) {
            boolean detail = setAccountDetail(depositeMoney);
            if (detail) {
                //DBConnection.getInstance().getConnection().commit();

                return true;
            }
        }
        DBConnection.getInstance().getConnection().rollback();
        return false;
    } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
        public static String actName;
    public static void setActName() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE CusID = ?");
        pstm.setObject(1,custId);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
           actName=resultSet.getString(2);
        }
        //SELECT * FROM account WHERE ActID = 'A001';
    }

}
