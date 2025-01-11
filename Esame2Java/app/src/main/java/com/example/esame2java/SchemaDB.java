package com.example.esame2java;

import android.provider.BaseColumns;

public class SchemaDB {
    // To prevent someone from accidentally instantiating the
    // schema class, give it an empty constructor.
    public SchemaDB() {
    }

    /* Inner class that defines the table contents */
    public static abstract class Tavola implements BaseColumns {
        public static final String TABLE_NAME = "studenti";
        public static final String COLUMN_NAME = "nome";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_EMAIL = "email";
    }

}
