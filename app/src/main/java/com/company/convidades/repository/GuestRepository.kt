package com.company.convidades.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.company.convidades.constants.DataBaseConstants
import com.company.convidades.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase: GuestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }

    }

    fun save(guestModel: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guestModel.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guestModel.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun update(guestModel: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guestModel.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guestModel.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"

            val args = arrayOf(guestModel.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)

            true
        } catch (ex: Exception) {
            false
        }


    }

    fun delete(id: Int): Boolean {
        return try {
            val db: SQLiteDatabase = guestDataBase.writableDatabase

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args: Array<String> = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

            true
        } catch (ex: Exception) {
            false
        }
    }

    fun findAll():List<GuestModel> {

        val guests = mutableListOf<GuestModel>()

        try {

            val db: SQLiteDatabase = guestDataBase.readableDatabase

            val projection: Array<String> = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection, null, null,
                null, null, null
            )

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    guests.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (ex: Exception){
            return guests
        }

        return guests
    }

    fun findAllByPresence(presence:Int): List<GuestModel>{
        val guests = mutableListOf<GuestModel>()

        try {

            val db: SQLiteDatabase = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT * FROM Guest WHERE presence = $presence",null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    guests.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (ex: Exception){
            return guests
        }
        return guests
    }

    fun findById(id: Int): GuestModel? {

        var guest: GuestModel? = null

        try {

            val db: SQLiteDatabase = guestDataBase.readableDatabase
            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args: Array<String> = arrayOf(id.toString())
            val projection: Array<String> = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection, selection, args,
                null, null, null
            )

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    guest = GuestModel(id, name, presence == 1)
                }
            }

            cursor.close()
        } catch (ex: Exception){
            return guest
        }

        return guest
    }

}