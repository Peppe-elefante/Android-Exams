package com.example.esame1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "score.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "scoreboard"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_SCORE = "score"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_NAME TEXT, "+
                "$COLUMN_SCORE INTEGER)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addScore(name: String, score: Int){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_NAME, name)
            put(COLUMN_SCORE, score)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllScores(): List<Score>{
        val scores = mutableListOf<Score>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_NAME, COLUMN_SCORE), null,null,null,null,null)
        with(cursor){
            while(moveToNext()){
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val score = getInt(getColumnIndexOrThrow(COLUMN_SCORE))
                scores.add(Score(name,score))
            }
            close()
        }
        db.close()
        return scores
    }
}