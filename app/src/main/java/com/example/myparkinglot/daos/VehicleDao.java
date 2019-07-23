package com.example.myparkinglot.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.myparkinglot.model.Vehicle;

import java.util.List;

@Dao
public interface VehicleDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    long ParkVehicle(Vehicle vehicle);

    @Query("DELETE FROM parking_lots")
    void deleteAll();

    @Query("SELECT * FROM parking_lots")
    LiveData<List<Vehicle>> getAllVehicle();

    @Query("SELECT * FROM parking_lots")
    List<Vehicle> getParkedVehicle();

    @Query("SELECT lot_number FROM parking_lots")
    List<Integer> getAllBookedLots();

    @Query("SELECT COUNT(lot_number) FROM parking_lots WHERE lot_number = (:lot)")
    Integer getParkedVehicle(int lot);

    @Query("SELECT COUNT(lot_number) FROM parking_lots WHERE vehicle_number = (:vehicle)")
    Integer getVehicleExists(int vehicle);
}
