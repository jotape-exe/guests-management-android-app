package com.company.convidades.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.company.convidades.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GuestRepository.getInstance(application)
    fun save() {
        TODO("Not yet implemented")
    }


}