package com.example.esame3java;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name and Version
    private static final String DATABASE_NAME = "ScoreDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table and Column Names
    public static final String TABLE_NAME = "score";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";

    // Create Table Query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_SCORE + " INTEGER NOT NULL)";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing table if it exists and recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert a Record
    public boolean insertScore(Score s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, s.getName());
        values.put(COLUMN_SCORE, s.getScore());

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1; // Return true if insert is successful
    }

    // Fetch All Records
    public Cursor getAllScores() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Update a Record
    public boolean updateScore(String name, int newScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, newScore);

        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_NAME + " = ?", new String[]{name});
        return rowsAffected > 0; // Return true if update is successful
    }

    // Delete a Record
    public boolean deleteScore(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_NAME, COLUMN_NAME + " = ?", new String[]{name});
        return rowsDeleted > 0; // Return true if delete is successful
    }

    // Delete All Records
    public void deleteAllScores() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}