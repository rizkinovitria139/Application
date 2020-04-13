package com.example.application;

import android.se.omapi.Session;

import com.example.application.model.Account;

public class Application extends android.app.Application {
    private static Account account;

    @Override
    public void onCreate() {

        super.onCreate();
        account = new Account("Budi");
    }


    public static Account getAccount() {
        return account;
    }
}
