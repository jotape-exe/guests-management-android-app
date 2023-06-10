package com.company.convidades.repository

import android.content.Context
import com.company.convidades.model.GuestModel

class GuestRepository(context: Context) {

    private val guestDataBase: GuestDAO = GuestDataBase.getDatabaseInstance(context).guestDAO()

    fun save(guest: GuestModel): Boolean {
        return guestDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0

    }

    fun delete(id: Int) {
        val guest = findById(id)
        guestDataBase.delete(guest)
    }

    fun findById(id: Int): GuestModel {
        return guestDataBase.findById(id)
    }

    fun findAll():List<GuestModel> {
        return  guestDataBase.findAll()
    }

    fun findAllByPresence(presence:Int): List<GuestModel> = guestDataBase.findAllByPresence(presence)

}