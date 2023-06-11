package com.company.convidades.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.convidades.model.GuestModel
import com.company.convidades.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val guestRepository = GuestRepository(application.applicationContext)
    private val allGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = allGuests

    fun finALl() {
        allGuests.value = guestRepository.findAll()
    }

    fun getByPresence(presence: Int){
        allGuests.value = guestRepository.findAllByPresence(presence)
    }

    fun delete(id: Int) {
        guestRepository.delete(id)
    }

}