package lk.ijse.sanasaBank.to;

public class OpenAccount {
    public OpenAccount(String actId, String actType, String memId, Double despositeMoney) {
    }

    public OpenAccount(String searchId, String searchName) {
        this.searchId = searchId;
        this.searchName = searchName;
    }

    public OpenAccount(String actId, String actTypeId, String custId, Double balance, String date) {
        this.actId = actId;
        this.actTypeId = actTypeId;
        this.custId = custId;
        this.balance = balance;
        this.date = date;
    }

     private   String searchId;
    private String searchName;
   private String actId;
   private String actTypeId;
    private String custId;
   private Double balance;
   private String date;

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getActTypeId() {
        return actTypeId;
    }

    public void setActTypeId(String actTypeId) {
        this.actTypeId = actTypeId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @Override
    public String toString() {
        return "OpenAccount{" +
                "searchId='" + searchId + '\'' +
                ", searchName='" + searchName + '\'' +
                ", actId='" + actId + '\'' +
                ", actTypeId='" + actTypeId + '\'' +
                ", custId='" + custId + '\'' +
                ", balance=" + balance +
                ", date='" + date + '\'' +
                '}';
    }
}
