package com.santiago.regionalcaucaworldskills.models.local.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.santiago.regionalcaucaworldskills.models.Constants

class DBHelper(
    context: Context?
) : SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants.TABLE_CREATE_CAT)
        db?.execSQL(Constants.TABLE_CREATE_REG)
        db?.execSQL(Constants.TABLE_CREATE_CAT_ID)
        db?.execSQL(Constants.TABLE_CREATE_PED)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(" DROP TABLE IF EXISTS " + Constants.TABLE_NAME_REG)
        db?.execSQL(" DROP TABLE IF EXISTS " + Constants.TABLE_NAME_CAT)
        db?.execSQL(" DROP TABLE IF EXISTS " + Constants.TABLE_NAME_CAT_ID)
        db?.execSQL(" DROP TABLE IF EXISTS " + Constants.TABLE_NAME_PED)
        onCreate(db)

    }
}