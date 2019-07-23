package com.example.myparkinglot;

import com.example.myparkinglot.model.Vehicle;
import com.example.myparkinglot.utils.ParkingLot;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {
    @Test
    public void testToCheckParkedVehicle(){
        ParkingLot plot = new ParkingLot();
        assertTrue("Valid Parked vehicle", plot.parkedVehicle instanceof HashMap);
    }

    @Test
    public void testToCheckWeCanParkVehicleOrNot(){
        Vehicle v = new Vehicle(100, 1000);
        ParkingLot plot = new ParkingLot();
        plot.parkVehicle(v, 1000);
        if ((v.id + " parked at lot no " + v.lot).equals("100 parked at lot no 1000")){
            assertTrue("Vehicle Parked", true);
        }else{
            assertFalse("Failed to park vehicle", true);
        }


    }
}

