package com.company.convidades.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.convidades.model.GuestModel
import com.company.convidades.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GuestRepository.getInstance(application)
    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel
    fun save(guest: GuestModel) {
        if (guest.id == 0){
            repository.save(guest)
        } else{
            repository.update(guest)
        }
    }

    fun get(id:Int) {
        guestModel.value = repository.findById(id)
    }


}