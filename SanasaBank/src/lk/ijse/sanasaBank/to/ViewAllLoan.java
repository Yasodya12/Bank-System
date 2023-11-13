package lk.ijse.sanasaBank.to;

public class ViewAllLoan {
   private String lid;
   private String lTypeId;
   private String actId;

   private String Period;
   private String date;
   private String amount;
   private String interest;
   private String Total;
   private String RemaingAmount;
   private String Reason;

    public ViewAllLoan(String lid, String lTypeId, String actId, String period, String date, String amount, String interest, String total, String remaingAmount, String reason) {
        this.lid = lid;
        this.lTypeId = lTypeId;
        this.actId = actId;
        Period = period;
        this.date = date;
        this.amount = amount;
        this.interest = interest;
        Total = total;
        RemaingAmount = remaingAmount;
        Reason = reason;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getlTypeId() {
        return lTypeId;
    }

    public void setlTypeId(String lTypeId) {
        this.lTypeId = lTypeId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getRemaingAmount() {
        return RemaingAmount;
    }

    public void setRemaingAmount(String remaingAmount) {
        RemaingAmount = remaingAmount;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    @Override
    public String toString() {
        return "ViewAllLoan{" +
                "lid='" + lid + '\'' +
                ", lTypeId='" + lTypeId + '\'' +
                ", actId='" + actId + '\'' +
                ", Period='" + Period + '\'' +
                ", date='" + date + '\'' +
                ", amount='" + amount + '\'' +
                ", interest='" + interest + '\'' +
                ", Total='" + Total + '\'' +
                ", RemaingAmount='" + RemaingAmount + '\'' +
                ", Reason='" + Reason + '\'' +
                '}';
    }
}
