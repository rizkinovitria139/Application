package com.example.application.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private int balance;
    int cardigan = 100000;
    int hoodie = 75000;
    int dress = 175000;
    int overall = 125000;
    private List<Transaction> transactions;


    public Account(String name) {
        this.name = name;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction.getType() == Transaction.Type.CARDIGAN) {
            balance += cardigan;
        } else if(transaction.getType() == Transaction.Type.HOODIE){
            balance += hoodie;
        }else if(transaction.getType() == Transaction.Type.DRESS){
            balance += dress;
        }else{
            balance += overall;
        }
        this.transactions.add(transaction);
    }

    public void removeTransaction(int index) {
        Transaction transaction = transactions.get(index);
        if (transaction.getType() == Transaction.Type.CARDIGAN) {
            balance -= cardigan;
        } else if(transaction.getType() == Transaction.Type.HOODIE){
            balance -= hoodie;
        }else if(transaction.getType() == Transaction.Type.DRESS){
            balance -= dress;
        }else{
            balance -= overall;
        }
        this.transactions.remove(transaction);
    }

    public void updateTransaction(int index, Transaction transaction) {
        this.transactions.set(index, transaction);
        this.balance = 0;
        for (Transaction t : transactions) {
            if (transaction.getType() == Transaction.Type.CARDIGAN) {
                balance += cardigan;
            } else if(transaction.getType() == Transaction.Type.HOODIE){
                balance += hoodie;
            }else if(transaction.getType() == Transaction.Type.DRESS){
                balance += dress;
            }else{
                balance += overall;
            }
        }
    }
}
