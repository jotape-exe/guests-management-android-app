package com.company.convidades.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.company.convidades.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM guest WHERE id = :id")
    fun findById(id: Int): GuestModel

    @Query("SELECT * FROM guest")
    fun findAll():List<GuestModel>

    @Query("SELECT * FROM guest WHERE presence = :presence")
    fun findAllByPresence(presence:Int): List<GuestModel>

}