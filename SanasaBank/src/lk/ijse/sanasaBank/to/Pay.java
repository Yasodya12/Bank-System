package lk.ijse.sanasaBank.to;

public class Pay {
   private String loanId;
   private String payId;
   private Double amount;
   private String payMethod;
   private String chequeNum;
   private String date;

    public Pay(String loanId) {
        this.loanId = loanId;
    }

    public Pay(String loanId, String payId, Double amount, String payMethod, String chequeNum, String date) {
        this.loanId = loanId;
        this.payId = payId;
        this.amount = amount;
        this.payMethod = payMethod;
        this.chequeNum = chequeNum;
        this.date = date;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getChequeNum() {
        return chequeNum;
    }

    public void setChequeNum(String chequeNum) {
        this.chequeNum = chequeNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "loanId='" + loanId + '\'' +
                ", payId='" + payId + '\'' +
                ", amount=" + amount +
                ", payMethod='" + payMethod + '\'' +
                ", chequeNum='" + chequeNum + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
