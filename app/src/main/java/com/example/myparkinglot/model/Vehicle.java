package com.example.myparkinglot.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

public class Vehicle {

    public Integer Id = 0;

    public Integer lot = 0;

    public Vehicle(){}

    public Vehicle(int lot) {
        this.setLot(lot);
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "Id=" + Id +
                ", lot=" + lot +
                '}';
    }
}
