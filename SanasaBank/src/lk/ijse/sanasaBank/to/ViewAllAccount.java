package lk.ijse.sanasaBank.to;

public class ViewAllAccount {
    public ViewAllAccount() {
    }

    public ViewAllAccount(String aciId, String actType, String custId, Double balance, String date) {
        this.aciId = aciId;
        this.actType = actType;
        this.custId = custId;
        this.balance = balance;
        this.date = date;
    }

  private   String aciId;
   private String actType;
  private   String custId;
   private Double balance;
   private String date;

    public String getAciId() {
        return aciId;
    }

    public void setAciId(String aciId) {
        this.aciId = aciId;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
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

    @Override
    public String toString() {
        return "ViewAllAccount{" +
                "aciId='" + aciId + '\'' +
                ", actType='" + actType + '\'' +
                ", custId='" + custId + '\'' +
                ", balance=" + balance +
                ", date='" + date + '\'' +
                '}';
    }
}
