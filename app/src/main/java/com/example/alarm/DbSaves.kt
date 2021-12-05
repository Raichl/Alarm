package com.example.alarm

import android.provider.BaseColumns

object DbSaves {
    const val dbVersion = 1
    const val dbName = "Saves"
    const val tableSaves = "ColorSaves"

    const val backColor = "BackColor"
    const val linearBackground = "LinearBackground"
    const val carType = "CarType"

    const val CREATE_TABLE_PERSONS = "CREATE TABLE IF NOT EXISTS $tableSaves (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$carType TEXT, $linearBackground INTEGER, $backColor INTEGER)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $tableSaves"
}