package com.example.esame2java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    final private static String CREATE_CMD =
            "CREATE TABLE "+SchemaDB.Tavola.TABLE_NAME+" ("
                    + SchemaDB.Tavola._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SchemaDB.Tavola.COLUMN_NAME + " TEXT NOT NULL, "
                    + SchemaDB.Tavola.COLUMN_NUMBER + " TEXT, "
                    + SchemaDB.Tavola.COLUMN_EMAIL+ "TEXT);";

    final private static Integer VERSION = 1;
    final private Context context;

    public DbHelper(Context context) {
        super(context, SchemaDB.Tavola.TABLE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Create (Insert) Operation
    public void insertAllContatto(List<Contatto> contatti) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(Contatto c : contatti) {

            String name = c.getName();
            String number = c.getTel();
            String email = c.getEmail();

            ContentValues values = new ContentValues();
            values.put(SchemaDB.Tavola.COLUMN_NAME, name);
            values.put(SchemaDB.Tavola.COLUMN_NUMBER, number);
            values.put(SchemaDB.Tavola.COLUMN_EMAIL, email);
        }

    }

    public void insertContatto(Contatto c) {
        SQLiteDatabase db = this.getWritableDatabase();


        String name = c.getName();
        String number = c.getTel();
        String email = c.getEmail();

        ContentValues values = new ContentValues();
        values.put(SchemaDB.Tavola.COLUMN_NAME, name);
        values.put(SchemaDB.Tavola.COLUMN_NUMBER, number);
        values.put(SchemaDB.Tavola.COLUMN_EMAIL, email);


    }

    void deleteDatabase() {
        context.deleteDatabase(SchemaDB.Tavola.TABLE_NAME);
    }

    public List<Contatto> getAllContacts() {
        List<Contatto> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SchemaDB.Tavola.TABLE_NAME, // The table name
                new String[] { SchemaDB.Tavola.COLUMN_NAME, SchemaDB.Tavola.COLUMN_EMAIL, SchemaDB.Tavola.COLUMN_NUMBER }, // Columns to retrieve
                null, // WHERE clause
                null, // WHERE arguments
                null, // GROUP BY clause
                null, // HAVING clause
                null // ORDER BY clause
        );

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(SchemaDB.Tavola.COLUMN_NAME));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(SchemaDB.Tavola.COLUMN_EMAIL));
                    String number = cursor.getString(cursor.getColumnIndexOrThrow(SchemaDB.Tavola.COLUMN_NUMBER));
                    contacts.add(new Contatto(name, number,email));
                }
            } finally {
                cursor.close();
            }
        }
        db.close();
        return contacts;
    }


}
