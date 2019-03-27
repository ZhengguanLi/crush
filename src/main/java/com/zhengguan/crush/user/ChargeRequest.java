package com.zhengguan.crush.user;

public class ChargeRequest {
    public enum Currency {
        EUR, USD;
    }

    private String description;
    private String amountStr;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;

    public ChargeRequest() {
    }

    public ChargeRequest(String description, int amount, Currency currency, String stripeEmail, String stripeToken) {
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.stripeEmail = stripeEmail;
        this.stripeToken = stripeToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public String getAmountStr() {
        return amountStr;
    }

    public void setAmountStr(String amountStr) {
        this.amountStr = amountStr;
        this.amount = (int) (Double.parseDouble(amountStr.substring(1)) * 100);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getStripeEmail() {
        return stripeEmail;
    }

    public void setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
    }

    public String getStripeToken() {
        return stripeToken;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }

    @Override
    public String toString() {
        return "ChargeRequest{" +
                "description='" + description + '\'' +
                ", amountStr='" + amountStr + '\'' +
                ", amount=" + amount +
                ", currency=" + currency +
                ", stripeEmail='" + stripeEmail + '\'' +
                ", stripeToken='" + stripeToken + '\'' +
                '}';
    }
}
