package lk.ijse.sanasaBank.to;

public class DepositeMoney {
    public DepositeMoney() {
    }

    public DepositeMoney(String actId) {
        this.actId = actId;
    }

    public DepositeMoney(String actId, String transactionId, String depositeName, Double depositeAmoubt, String transType, String date) {
        this.actId = actId;
        this.transactionId = transactionId;
        this.depositeName = depositeName;
        this.depositeAmoubt = depositeAmoubt;
        this.transType = transType;
        this.date = date;
    }

   private String actId;

   private String transactionId;

  private   String depositeName;

    private Double depositeAmoubt;

   private String transType;
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

    public String getDepositeName() {
        return depositeName;
    }

    public void setDepositeName(String depositeName) {
        this.depositeName = depositeName;
    }

    public Double getDepositeAmoubt() {
        return depositeAmoubt;
    }

    public void setDepositeAmoubt(Double depositeAmoubt) {
        this.depositeAmoubt = depositeAmoubt;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DepositeMoney{" +
                "actId='" + actId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", depositeName='" + depositeName + '\'' +
                ", depositeAmoubt='" + depositeAmoubt + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
