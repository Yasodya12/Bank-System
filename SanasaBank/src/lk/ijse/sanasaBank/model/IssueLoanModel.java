package lk.ijse.sanasaBank.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sanasaBank.controller.GurdianLoanController;
import lk.ijse.sanasaBank.controller.IssueLoanController;
import lk.ijse.sanasaBank.controller.IssueLoanIIController;
import lk.ijse.sanasaBank.db.DBConnection;
import lk.ijse.sanasaBank.to.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IssueLoanModel {
    public String reslultId;
    public String resultName;
    public String nic;


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

    public boolean searchId(searchMember searchMember) throws SQLException, ClassNotFoundException {


            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE CusID = ?");
            //comers here
            pstm.setObject(1, searchMember.getId());

            ResultSet result = pstm.executeQuery();

            if (result.next()) {

                reslultId = result.getString(1);
                resultName = result.getString(2);
                nic = result.getString(8);

                return true;
            }

        return false;
    }

    public boolean searchNme(searchMember searchMember) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE firstName = ?");
        //comers here
        pstm.setObject(1, searchMember.getName());

        ResultSet result = pstm.executeQuery();


        if(result.next()){

            reslultId= result.getString(1);
            resultName=result.getString(2);
            nic=result.getString(8);

            return true;
        }


        return false;
    }

    public ArrayList<OpenAccount> viewAllAccounts(searchMember searchMember) throws SQLException, ClassNotFoundException, NullPointerException{
        ArrayList<OpenAccount> allAccount=new ArrayList<>();
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM account WHERE CusID=? ;");
        pstm.setObject(1, searchMember.getId());
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){

            allAccount.add(new OpenAccount(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Double.valueOf(resultSet.getString(4)),
                    resultSet.getString(5)
            ));

        }

        return allAccount;
    }

    public void getId(searchMember searchMember) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer WHERE firstName = ?");
        //comers here
        pstm.setObject(1, searchMember.getName());
        ResultSet result = pstm.executeQuery();
        if(result.next()){
            reslultId=result.getString(1);
        }
    }

    public ArrayList<OpenAccount> viewAllAccountsName(searchMember searchMember) throws SQLException, ClassNotFoundException, NullPointerException{
        getId(searchMember);
        ArrayList<OpenAccount> allAccount=new ArrayList<>();
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM account WHERE CusID=? ;");
        pstm.setObject(1, reslultId);
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){

            allAccount.add(new OpenAccount(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Double.valueOf(resultSet.getString(4)),
                    resultSet.getString(5)
            ));

        }

        return allAccount;
    }

    public static ArrayList<ViewAllLoan> viewAllLoans() throws SQLException, ClassNotFoundException {
        ArrayList<ViewAllLoan> allLoans=new ArrayList<>();
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM loan WHERE ActID=?;");
        pstm.setObject(1,IssueLoanController.actIdForLoan);
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
        return allLoans;
    }

    public static ObservableList<String> loanType() throws SQLException, ClassNotFoundException {

        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select LoanTypename from  loantype;");
        ResultSet result = pstm.executeQuery();

        ObservableList<String> loanType = FXCollections.observableArrayList();
        while (result.next()){
            loanType.add(result.getString(1));
        }
        return loanType;
    }
    public static double interestRate;


    public static double interest;
    public static double tot;

    public static double monthInstalmemt;
    public static void setLoanDetail(IssueLoanII issueLoanII) throws SQLException, ClassNotFoundException {

        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("select Interest from  loantype WHERE LoanTypename=?;");
        //select Interest from  loantype WHERE LoanTypename="Personal Loan";

        pstm.setObject(1, issueLoanII.getLoanType());

        ResultSet result = pstm.executeQuery();

        while (result.next()){

           interestRate= Double.parseDouble(((result.getString(1))));

        }


        if(issueLoanII.getPeriod()=="1 Year"){
        interest=issueLoanII.getAmount()*interestRate/100;
        tot=interest+issueLoanII.getAmount();
        monthInstalmemt=tot/12;
        }

        if(issueLoanII.getPeriod()=="3 Year"){
            interest=issueLoanII.getAmount()*interestRate*3/100;
            tot=interest+issueLoanII.getAmount();
            monthInstalmemt=tot/36;
        }
        if(issueLoanII.getPeriod()=="5 Year"){
            interest=issueLoanII.getAmount()*interestRate*5/100;
            tot=interest+issueLoanII.getAmount();
            monthInstalmemt=tot/60;
        }

    }

    public static boolean AddLoanDetail(IssueLoanII issueLoanII) throws SQLException, ClassNotFoundException {

            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO loangurdian VALUES (?,?);");
            pstm.setObject(1, GurdianLoanController.gurdianId);
            pstm.setObject(2,issueLoanII.getLoanId());
            //Update account set Balance=100 where ActID='A001';   INSERT INTO loangurdian VALUES ('G001','L003');


        int executeUpdate = pstm.executeUpdate();

        if(executeUpdate>0){
            return true;
        }
        return false;
    }
    public static String loantypeId;
    public static void getLoanTypeID(IssueLoanII issueLoanII) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement("SELECT LoanTypeID FROM loantype WHERE LoanTypename=?");
        //SELECT LoanTypeID FROM loantype WHERE LoanTypename='Personal Loan';
        pstm.setObject(1, issueLoanII.getLoanType());
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            loantypeId=resultSet.getString(1);
        }
    }

  public static boolean addLoanGurdian(IssueLoanII issueLoanII) throws SQLException, ClassNotFoundException {
        try {

            if (issueLoanII.getPeriod() == "1 Year") {
                interest = issueLoanII.getAmount() * interestRate / 100;
                tot = interest + issueLoanII.getAmount();
                monthInstalmemt = tot / 12;
            }

            if (issueLoanII.getPeriod() == "3 Year") {
                interest = issueLoanII.getAmount() * interestRate * 3 / 100;
                tot = interest + issueLoanII.getAmount();
                monthInstalmemt = tot / 36;
            }
            if (issueLoanII.getPeriod() == "5 Year") {
                interest = issueLoanII.getAmount() * interestRate * 5 / 100;
                tot = interest + issueLoanII.getAmount();
                monthInstalmemt = tot / 60;
            }
            DBConnection.getInstance().getConnection().setAutoCommit(false);


             getLoanTypeID(issueLoanII);
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO loan VALUES (?,?,?,?,?,?,?,?,?,?)");
        //INSERT INTO loan VALUES ('L004','Personal Loan','A002','1 year','23',10000,100,11000,11000,'personal');"

        pstm.setObject(1, issueLoanII.getLoanId());
        pstm.setObject(2, loantypeId);
        pstm.setObject(3, IssueLoanController.actIdForLoan);
        pstm.setObject(4, issueLoanII.getPeriod());
        pstm.setObject(5, issueLoanII.getDate());
        pstm.setObject(6, issueLoanII.getAmount());
        pstm.setObject(7, interest);
        pstm.setObject(8, tot);
        pstm.setObject(9, tot);
        pstm.setObject(10, issueLoanII.getReason());

            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate>0) {
                boolean addLoanDetail = AddLoanDetail(issueLoanII);

                if (addLoanDetail) {

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


}
