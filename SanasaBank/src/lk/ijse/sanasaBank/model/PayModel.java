package lk.ijse.sanasaBank.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sanasaBank.controller.IssueLoanController;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.DepositeMoney;
import lk.ijse.sanasaBank.to.IssueLoanII;
import lk.ijse.sanasaBank.to.Pay;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayModel {
    public static ObservableList<String> LoanID() throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select LID from  loan;");
        ResultSet result = pstm.executeQuery();

        ObservableList<String> loanId = FXCollections.observableArrayList();
        while (result.next()){
            loanId.add(result.getString(1));
        }

        return loanId;
    }

    public static String actIdFor;
    public static String amount;
    public static String interest;
    public static String tot;
    public static String remain;
    public static Double monthInst;

    public static void setLoanDetail(Pay pay) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select * from  loan WHERE LID=?;");
        pstm.setObject(1, pay.getLoanId());
        ResultSet result = pstm.executeQuery();
        while (result.next()){
            actIdFor=result.getString(3);
            amount=result.getString(6);
            interest=result.getString(7);
            tot=result.getString(8);
            remain=result.getString(9);
            monthInst=45.23;
            if(result.getString(4).equals("1 Year")){
                monthInst=Double.parseDouble(tot)/12;
            } else if (result.getString(4).equals("3 Year")) {
                monthInst=Double.parseDouble(tot)/36;
            }else {
                monthInst=Double.parseDouble(tot)/60;
            }
        }

    }
    public static boolean setInstalment(Pay pay) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO instalmentpayment VALUES (?,?,?,?)");
        pstm.setObject(1, pay.getPayId());
        pstm.setObject(2, pay.getLoanId());
        pstm.setObject(3, pay.getAmount());
        pstm.setObject(4, pay.getPayMethod());

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            return true;
        }
        return false;
    }

    public static boolean updateLoan(Pay pay) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("Update loan set RemaingAmount=RemaingAmount-? where LID=?;");
            pstm.setObject(1, pay.getAmount());
            pstm.setObject(2,pay.getLoanId());

            //Update loan set RemaingAmount=RemaingAmount-? where LID='L001';
            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate > 0) {
                boolean setInstalment = setInstalment(pay);
                if (setInstalment) {
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

        public static boolean setInstamentForCheque(Pay pay) throws SQLException, ClassNotFoundException {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("Update loan set RemaingAmount=RemaingAmount-? where LID=?;");
            pstm.setObject(1, pay.getAmount());
            pstm.setObject(2,pay.getLoanId());
            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate>0){
                return true;
            }
            return false;
        }

    public static boolean payCheque(Pay pay) throws SQLException, ClassNotFoundException {
        try {

            DBConnection.getInstance().getConnection().setAutoCommit(false);


            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO cheque (purpose, amount, date ) VALUES(?,?,?)");
            //INSERT INTO loan VALUES ('L004','Personal Loan','A002','1 year','23',10000,100,11000,11000,'personal');"
            pstm.setObject(1, "Loan Settle");
            pstm.setObject(2, Double.valueOf(pay.getAmount()));
            pstm.setObject(3,pay.getDate());


            int executeUpdate = pstm.executeUpdate();
            System.out.println(executeUpdate);
            if (executeUpdate>0) {
                boolean setInstalment = setInstalment(pay);

                if (setInstalment) {
                    boolean setInstamentForCheque = setInstamentForCheque(pay);
                    if(setInstamentForCheque){
                        return true;
                    }
                    //DBConnection.getInstance().getConnection().commit();
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }
}


