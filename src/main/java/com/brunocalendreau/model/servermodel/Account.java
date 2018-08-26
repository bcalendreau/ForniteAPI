package com.brunocalendreau.model.servermodel;

public class Account {
    private String accountId;
    private int value;
    private int rank;

    public String getAccountId() {
        return accountId;
    }

    public int getValue() {
        return value;
    }

    public int getRank() {
        return rank;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}