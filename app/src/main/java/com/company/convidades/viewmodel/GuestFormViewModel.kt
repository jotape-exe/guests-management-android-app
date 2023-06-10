package com.company.convidades.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.convidades.model.GuestModel
import com.company.convidades.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GuestRepository(application)
    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest
    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.save(guest)) {
                _saveGuest.value = "Salvo com sucesso!"
            } else {
                _saveGuest.value = "Falha ao salvar!"
            }
        } else {
            if (repository.update(guest)) {
                _saveGuest.value = "Atualizado com sucesso!"
            } else {
                _saveGuest.value = "Falha ao atualizar!"
            }

        }
    }

    fun get(id: Int) {
        guestModel.value = repository.findById(id)
    }


}