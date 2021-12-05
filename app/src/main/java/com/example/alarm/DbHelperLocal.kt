package com.example.alarm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelperLocal (context: Context) : SQLiteOpenHelper(context, DbSaves.dbName,
    null, DbSaves.dbVersion){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbSaves.CREATE_TABLE_PERSONS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DbSaves.SQL_DELETE_TABLE)
    }

}