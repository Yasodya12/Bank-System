package lk.ijse.sanasaBank.model;

import lk.ijse.sanasaBank.controller.IssueLoanController;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.ViewAllLoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewAllLoanModel {
    public static ArrayList<ViewAllLoan> viewAllLoans() throws SQLException, ClassNotFoundException {
        ArrayList<ViewAllLoan> allLoans=new ArrayList<>();
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM loan;");

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            allLoans.add(new ViewAllLoan(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            ));

        }
        System.out.println(allLoans);
        return allLoans;
    }
}
