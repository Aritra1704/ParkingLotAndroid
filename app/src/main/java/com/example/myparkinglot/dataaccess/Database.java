package com.example.myparkinglot.dataaccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import com.example.myparkinglot.model.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Database extends SQLiteOpenHelper {

    /**
     * Database specific constant declarations
     */
    private SQLiteDatabase db;
    public static final String DATABASE_NAME = "parking_database.db";
    public static final int DATABASE_VERSION = 1;
    private Context mContext;

    public static final String PARKING_LOT_TABLE_NAME = "parking_lots";

    static final String CREATE_PARKING_LOT_TABLE =
            " CREATE TABLE IF NOT EXISTS " + PARKING_LOT_TABLE_NAME +
                    " ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " lot_number INTEGER," +
                    " vehicle_number INTEGER UNIQUE" +
                    ");";

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PARKING_LOT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_PARKING_LOT_TABLE);
        onCreate(db);
    }

    public long ParkVehicle(Vehicle vehicle, int vehicle_number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lot_number", vehicle.getLot());
        contentValues.put("vehicle_number", vehicle_number);
        long result = db.insert(PARKING_LOT_TABLE_NAME, null, contentValues);
        return result;
    }

    public Cursor getParkedVehicle() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKING_LOT_TABLE_NAME, new String[]{"id", "lot_number"},null, null, null, null, null);
        return cursor;
    }

    public Cursor getParkedVehicle(int vehicleId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKING_LOT_TABLE_NAME, new String[]{"id", "lot_number"},"vehicle_number == " + vehicleId, null, null, null, null);
        return cursor;
    }

    public Cursor getAllBookedLots() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKING_LOT_TABLE_NAME, new String[]{"vehicle_number", "lot_number"},null, null, null, null, null);
        return cursor;
    }
}
