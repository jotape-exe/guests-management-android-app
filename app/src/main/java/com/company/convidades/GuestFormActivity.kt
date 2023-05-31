package com.company.convidades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.company.convidades.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]
        binding.radioPresent.isChecked = true

        binding.buttonSave.setOnClickListener {
            if (it.id == R.id.button_save){

            }
        }
    }
}