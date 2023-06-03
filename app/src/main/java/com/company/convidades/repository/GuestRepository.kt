package com.company.convidades.repository

import android.content.ContentValues
import android.content.Context
import com.company.convidades.constants.DataBaseConstants
import com.company.convidades.model.GuestModel
import java.sql.SQLException

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

    fun findById(id: Int) {

    }

    fun delete(id: Int):Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

            true
        } catch (ex: Exception) {
            false
        }
    }

}