package com.company.convidades.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.company.convidades.R
import com.company.convidades.databinding.ActivityGuestFormBinding
import com.company.convidades.model.GuestModel
import com.company.convidades.viewmodel.GuestFormViewModel

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
                val name = binding.editName.text.toString()
                val present = binding.radioPresent.isChecked

                val entity = GuestModel(0, name, present)
                viewModel.save(entity)
            }
        }
    }
}