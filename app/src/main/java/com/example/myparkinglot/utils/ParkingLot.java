package com.example.myparkinglot.utils;

import android.content.Context;

import com.example.myparkinglot.daos.VehicleDao;
import com.example.myparkinglot.model.Vehicle;

import java.util.HashMap;

public class ParkingLot {

    private Context context;
    private VehicleDao dao;
    public HashMap<Integer, Vehicle> parkedVehicle = new HashMap<>();
    public HashMap<Integer, Integer> vehicle_lot_data = new HashMap<>();

    public ParkingLot(){}

    public String parkVehicle(Vehicle vehicle, int lot) {
        if(isLotBooked(lot))
            return "lot already taken";
        else if(isVehicleExists(vehicle.getLot()))
            return "Vehicle already present";
        else {
            return "Vehicle Parked";
        }
    }

    public boolean isLotBooked(int lot) {
        if(vehicle_lot_data != null && vehicle_lot_data.size() > 0) {
            if(vehicle_lot_data.containsKey(lot))
                return true;
            else
                return false;
        }
        return false;
    }

    public boolean isVehicleExists(int vehicleId) {
        if(parkedVehicle != null && parkedVehicle.size() > 0) {
            if(parkedVehicle.containsKey(vehicleId))
                return true;
            else
                return false;
        }
        return false;
    }
}
