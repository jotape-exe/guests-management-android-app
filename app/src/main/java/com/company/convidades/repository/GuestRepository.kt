package com.company.convidades.repository

import android.content.Context

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

        fun save(){

        }

        fun update(){

        }

        fun findById(id: Int){

        }

        fun delete(){

        }

    }

}