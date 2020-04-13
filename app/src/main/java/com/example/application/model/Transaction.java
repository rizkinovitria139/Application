package com.example.application.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Type;

public class Transaction implements Parcelable{
    public enum Type{
        CARDIGAN,
        HOODIE,
        DRESS,
        OVERALL
    }

    private String nama;
    private Type type;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Transaction(String nama, Type type) {
        this.nama = nama;
        this.type = type;
    }

    protected Transaction(Parcel in) {
        this.nama = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
    }
}
