package com.company.convidades.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.company.convidades.constants.DataBaseConstants
import com.company.convidades.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase() : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: GuestDataBase
        fun getDatabaseInstance(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized){
                //Not Deadlock
                synchronized(GuestDataBase::class) {
                    INSTANCE =  Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations(MIGRATION_SQLITE_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_SQLITE_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                TODO("Not yet implemented")
            }


        }
    }
}