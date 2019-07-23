package com.example.myparkinglot;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.database.Cursor;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arpaul.utilitieslib.PermissionUtils;
import com.arpaul.utilitieslib.StringUtils;
import com.example.myparkinglot.dataaccess.Database;
import com.example.myparkinglot.model.Vehicle;
import com.example.myparkinglot.utils.ParkingLot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vehicle_number_field)
    protected EditText vehicle_number_field;
    @BindView(R.id.lot_number_field)
    protected EditText vehicle_lot_number_field;
    @BindView(R.id.Parknow_btn)
    protected Button btnParknow;
    @BindView(R.id.message)
    protected TextView tvMessage;

    public Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * All the Best, Your code goes here
         * Team Skillenza
         */
        ButterKnife.bind(this);

        db = new Database(MainActivity.this);
    }

    public void ParkVehicleNow(View view) {
        String vehicle_id_field_txt = vehicle_number_field.getText().toString();
        String vehicle_lot_number_field_txt = vehicle_lot_number_field.getText().toString();

        park(StringUtils.getInt(vehicle_id_field_txt), StringUtils.getInt(vehicle_lot_number_field_txt));
    }

    private void park(final int vehicle_id_field_txt,final int vehicle_lot_number_field_txt) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                ParkingLot pl = new ParkingLot();

                fetchParkedVehicle(pl);

                fetchVehicleLot(pl);

                Vehicle vehicleDetail = new Vehicle();
                vehicleDetail.setLot(vehicle_lot_number_field_txt);
                String result = pl.parkVehicle(vehicleDetail, vehicle_id_field_txt);
                if(result.equalsIgnoreCase("Vehicle Parked")) {
                    long isInserted = db.ParkVehicle(vehicleDetail, vehicle_id_field_txt);
                    if(isInserted > 0)
                        result = "Vehicle Parked";
                }

                final String showToUser = result;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, showToUser, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();

    }

    private void fetchParkedVehicle(ParkingLot pl) {
        Cursor cursor = db.getParkedVehicle();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int lot_number = cursor.getInt(cursor.getColumnIndex("lot_number"));

                Vehicle vehicle = new Vehicle();
                vehicle.setId(id);
                vehicle.setLot(lot_number);
                pl.parkedVehicle.put(lot_number, vehicle);
            } while(cursor.moveToNext());
        }
        if(cursor != null && !cursor.isClosed())
            cursor.close();
    }

    private void fetchVehicleLot(ParkingLot pl) {
        Cursor cursor = db.getAllBookedLots();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                int lot_number = cursor.getInt(cursor.getColumnIndex("lot_number"));
                int vehicle_number = cursor.getInt(cursor.getColumnIndex("vehicle_number"));
                pl.vehicle_lot_data.put(vehicle_number, lot_number);
            } while(cursor.moveToNext());
        }

        if(cursor != null && !cursor.isClosed())
            cursor.close();
    }
}
