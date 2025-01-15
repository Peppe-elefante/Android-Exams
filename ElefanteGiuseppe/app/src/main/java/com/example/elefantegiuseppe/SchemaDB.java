package com.example.elefantegiuseppe;

import android.provider.BaseColumns;

public class SchemaDB {
    public SchemaDB() {
    }

    public static abstract class Tavola implements BaseColumns {
        public static final String TABLE_NAME = "oggetti";
        public static final String COLUMN_NAME = "nome";
        public static final String COLUMN_NUMBER = "numero";
    }

}
