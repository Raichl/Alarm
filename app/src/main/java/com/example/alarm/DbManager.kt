package com.example.alarm

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log

class DbManager(context: Context) {
    private val dbHelper = DbHelperLocal(context)
    private var db: SQLiteDatabase? = null

    fun openDb() {
        db = dbHelper.writableDatabase
    }
    fun closeDb(){
        dbHelper.close()
    }

    fun getSaves():SavesInfo?{
        var saves:SavesInfo? = null

        val cursor = db?.query(DbSaves.tableSaves, null,
            null, null,
            null, null, null,"1")
        while (cursor?.moveToNext()!!){
            val carType = cursor.getString(cursor.getColumnIndex(DbSaves.carType))
            val backColor = cursor.getInt(cursor.getColumnIndex(DbSaves.backColor))
            val lineColor = cursor. getInt(cursor.getColumnIndex(DbSaves.linearBackground))
            saves = SavesInfo(carType,lineColor,backColor)
        }
        cursor.close()
        return saves
    }

    fun saveNew(addSave: SavesInfo) {
        val values = ContentValues().apply {
            put(DbSaves.linearBackground,addSave.lineColor)
            put(DbSaves.backColor,addSave.backgroundColor)
            put(DbSaves.carType,addSave.carType)
        }
        db?.insert(DbSaves.tableSaves,null,values)
    }

    fun updateSaves(savesInfo: SavesInfo) {
        val values = ContentValues().apply {
            put(DbSaves.linearBackground,savesInfo.lineColor)
            put(DbSaves.backColor,savesInfo.backgroundColor)
            put(DbSaves.carType,savesInfo.carType)
        }
        db?.update(DbSaves.tableSaves,values,"${BaseColumns._ID} = ?", arrayOf("1"))
    }


}