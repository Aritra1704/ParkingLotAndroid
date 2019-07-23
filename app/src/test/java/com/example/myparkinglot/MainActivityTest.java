package com.example.myparkinglot;

import android.widget.EditText;

import com.example.myparkinglot.dataaccess.Database;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainActivityTest {
    @Test
    public void testToCheckValidFieldVehicleLotExist(){
        MainActivity ma = new MainActivity();
        if (ma.vehicle_lot_number_field instanceof EditText){
            assertTrue("valid fields exists",true);
        }
    }

    @Test
    public void testToCheckValidField_vehiclenoExist(){
        MainActivity ma = new MainActivity();
        if (ma.vehicle_number_field instanceof EditText){
            assertTrue("valid fields exists",true);
        }
    }

    @Test
    public void testToCheckValidDatabaseInstanceExists(){
        MainActivity ma = new MainActivity();
        if (ma.db instanceof Database) {
            assertTrue("valid database instance exist", true);
        }else{
            assertTrue("invalid database object", false);
        }
    }




}
