package com.example.myparkinglot;

import com.example.myparkinglot.model.Vehicle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VehicleTest {

    @Test
    public void testToCreateVehicle(){
        Vehicle vehicle = new Vehicle(007, 12);


        assertEquals(vehicle, vehicle);
    }

    @Test
    public void testToCheckValidVehicleId(){
        Vehicle vehicle = new Vehicle(100, 10);
        assertTrue("Valied Vehicle Number", vehicle instanceof Vehicle && vehicle.id instanceof Integer);
    }

    @Test
    public void testToCheckValidVehicleLot(){
        Vehicle vehicle = new Vehicle(100, 10);
        assertTrue("Valied Lot Number", vehicle instanceof Vehicle && vehicle.lot instanceof Integer);
    }

    @Test
    public void testToGetVehicleNumber(){
        Vehicle vehicle = new Vehicle(100, 10);
        assertTrue("Get vehicle Id", vehicle.getId() instanceof Integer );
    }

    @Test
    public void testToGetVehicleLotNumber(){
        Vehicle vehicle = new Vehicle(100, 10);
        assertTrue("Get vehicle Lot", vehicle.getLot() instanceof Integer );
    }
}
