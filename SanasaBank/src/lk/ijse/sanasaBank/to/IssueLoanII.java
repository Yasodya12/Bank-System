package lk.ijse.sanasaBank.to;

public class IssueLoanII {
   private String loanId;
   private String loanType;
   private String period;
    private String date;
   private int amount;

   private String reason;

    public IssueLoanII(String loanId, String loanType, String period, String date, int amount, String reason) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.period = period;
        this.amount = amount;
        this.date = date;
        this.reason = reason;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "IssueLoanII{" +
                "loanId='" + loanId + '\'' +
                ", loanType='" + loanType + '\'' +
                ", period='" + period + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
