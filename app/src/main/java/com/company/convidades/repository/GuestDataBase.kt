package com.company.convidades.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.company.convidades.constants.DataBaseConstants

class GuestDataBase(
    context: Context
) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object{
        private const val NAME:String = "guestdb"
        private const val VERSION:Int = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE ${DataBaseConstants.GUEST.TABLE_NAME} (" +
                "${DataBaseConstants.GUEST.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DataBaseConstants.GUEST.COLUMNS.NAME} TEXT," +
                "${DataBaseConstants.GUEST.COLUMNS.PRESENCE} INTEGER);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}