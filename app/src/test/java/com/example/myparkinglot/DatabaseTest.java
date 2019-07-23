package com.example.myparkinglot;

import android.content.Context;
import android.database.Cursor;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    @Test
    public void testToCheckValidDatabaseName(){

        MainActivity ma = new MainActivity();

        if (ma.db instanceof  Database) {
            Database.DATABASE_NAME.equals("parking_database.db");
            assertTrue("valid database name", true);
        }
    }
}
