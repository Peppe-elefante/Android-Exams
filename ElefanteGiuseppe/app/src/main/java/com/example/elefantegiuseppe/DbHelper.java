package com.example.elefantegiuseppe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    final private static String CREATE_CMD =
            "CREATE TABLE "+SchemaDB.Tavola.TABLE_NAME+" ("
                    + SchemaDB.Tavola._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SchemaDB.Tavola.COLUMN_NAME + " TEXT NOT NULL, "
                    + SchemaDB.Tavola.COLUMN_NUMBER + " TEXT);";
                    

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
    public void insertAllOggetto(List<Oggetto> oggetti) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            for (Oggetto c : oggetti) {
                String name = c.getNome();
                int number = c.getNumero();

                ContentValues values = new ContentValues();
                values.put(SchemaDB.Tavola.COLUMN_NAME, name);
                values.put(SchemaDB.Tavola.COLUMN_NUMBER, number);

                long result = db.insert(SchemaDB.Tavola.TABLE_NAME, null, values);

                if (result == -1) {

                    Log.e("database", "inserimento fallito");
                }
            }
        } finally {
            db.close(); // Ensure the database is closed
        }
    }

    public void insertOggetto(Oggetto c) {
        SQLiteDatabase db = this.getWritableDatabase();


        String name = c.getNome();
        String number = String.valueOf(c.getNumero());

        ContentValues values = new ContentValues();
        values.put(SchemaDB.Tavola.COLUMN_NAME, name);
        values.put(SchemaDB.Tavola.COLUMN_NUMBER, number);

    }

    public void updateOggetto(Oggetto o){

    }

    void deleteDatabase() {
        context.deleteDatabase(SchemaDB.Tavola.TABLE_NAME);
    }

    public List<Oggetto> getAllOggetti() {
        List<Oggetto> oggetti = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SchemaDB.Tavola.TABLE_NAME,
                new String[] { SchemaDB.Tavola.COLUMN_NAME, SchemaDB.Tavola.COLUMN_NUMBER },
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(SchemaDB.Tavola.COLUMN_NAME));
                    String number = cursor.getString(cursor.getColumnIndexOrThrow(SchemaDB.Tavola.COLUMN_NUMBER));
                    oggetti.add(new Oggetto(name, Integer.parseInt(number)));
                }
            } finally {
                cursor.close();
            }
        }
        db.close();
        return oggetti;
    }


}
