package com.company.convidades.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.convidades.model.GuestModel
import com.company.convidades.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val guestRepository = GuestRepository.getInstance(application.applicationContext)
    private val allGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = allGuests

    fun finALl() {
        allGuests.value = guestRepository.findAll()
    }

    fun delete(id: Int) {
        guestRepository.delete(id)
    }

}