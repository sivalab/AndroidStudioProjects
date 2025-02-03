package com.example.myfinances;

public class Finance {
    private int financeID;
    private String accountnumber;
    private String accounttype;
    private String initialbalance;
    private String currentbalance;
    private String paymentamount;
    private String interestrate;

    public Finance() {
        financeID = -1;
    }

    public int getFinanceID() {
        return financeID;
    }

    public void setFinanceID(int financeID) {
        this.financeID = financeID;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getInitialbalance() {
        return initialbalance;
    }

    public void setInitialbalance(String initialbalance) {
        this.initialbalance = initialbalance;
    }

    public String getCurrentbalance() {
        return currentbalance;
    }

    public void setCurrentbalance(String currentbalance) {
        this.currentbalance = currentbalance;
    }

    public String getPaymentamount() {
        return paymentamount;
    }

    public void setPaymentamount(String paymentamount) {
        this.paymentamount = paymentamount;
    }

    public String getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(String interestrate) {
        this.interestrate = interestrate;
    }
}
