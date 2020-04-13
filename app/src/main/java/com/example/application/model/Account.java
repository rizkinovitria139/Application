package com.example.application.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private int balance;
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
            balance += 100000;
        } else if(transaction.getType() == Transaction.Type.HOODIE){
            balance += 75000;
        }else if(transaction.getType() == Transaction.Type.DRESS){
            balance += 175000;
        }else{
            balance += 12500;
        }
        this.transactions.add(transaction);
    }

    public void removeTransaction(int index) {
        Transaction transaction = transactions.get(index);
        if (transaction.getType() == Transaction.Type.CARDIGAN) {
            balance -= 100000;
        } else if(transaction.getType() == Transaction.Type.HOODIE){
            balance -= 75000;
        }else if(transaction.getType() == Transaction.Type.DRESS){
            balance -= 175000;
        }else{
            balance -= 12500;
        }
        this.transactions.remove(transaction);
    }

    public void updateTransaction(int index, Transaction transaction) {
        this.transactions.set(index, transaction);
        this.balance = 0;
        for (Transaction t : transactions) {
            if (transaction.getType() == Transaction.Type.CARDIGAN) {
                balance += 100000;
            } else if(transaction.getType() == Transaction.Type.HOODIE){
                balance += 75000;
            }else if(transaction.getType() == Transaction.Type.DRESS){
                balance += 175000;
            }else{
                balance += 12500;
            }
        }
    }
}
