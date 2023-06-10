package com.company.convidades.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.company.convidades.model.GuestModel

@Database(entities = [GuestModel::class], version = 2)
abstract class GuestDataBase : RoomDatabase() {
    abstract fun guestDAO(): GuestDAO
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
                database.execSQL("CREATE TABLE guest_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT NOT NULL, presence INTEGER NOT NULL)")

                database.execSQL("INSERT INTO guest_new (name, presence) SELECT name, presence FROM guest")

                database.execSQL("DROP TABLE guest")

                database.execSQL("ALTER TABLE guest_new RENAME TO guest")
            }

        }
    }
}