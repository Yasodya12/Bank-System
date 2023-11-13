package lk.ijse.sanasaBank.to;

public class WithDrawMoney {

    public WithDrawMoney() {
    }

    public WithDrawMoney(String actId) {
        this.actId = actId;
    }

    public WithDrawMoney(String actId, String transactionId, String withdrawName, Double withdrawAmoubt, String date) {
        this.actId = actId;
        this.transactionId = transactionId;
        WithdrawName = withdrawName;
        WithdrawAmoubt = withdrawAmoubt;
        this.date = date;
    }

   private String actId;

   private String transactionId;

   private  String WithdrawName;

  private   Double WithdrawAmoubt;

   private String date;

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getWithdrawName() {
        return WithdrawName;
    }

    public void setWithdrawName(String withdrawName) {
        WithdrawName = withdrawName;
    }

    public Double getWithdrawAmoubt() {
        return WithdrawAmoubt;
    }

    public void setWithdrawAmoubt(Double withdrawAmoubt) {
        WithdrawAmoubt = withdrawAmoubt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WithDrawMoney{" +
                "actId='" + actId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", WithdrawName='" + WithdrawName + '\'' +
                ", WithdrawAmoubt=" + WithdrawAmoubt +
                ", date='" + date + '\'' +
                '}';
    }
}
