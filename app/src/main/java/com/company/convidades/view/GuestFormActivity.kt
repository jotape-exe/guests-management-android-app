package com.company.convidades.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.company.convidades.R
import com.company.convidades.constants.DataBaseConstants
import com.company.convidades.databinding.ActivityGuestFormBinding
import com.company.convidades.model.GuestModel
import com.company.convidades.viewmodel.GuestFormViewModel
import com.google.android.material.snackbar.Snackbar

class GuestFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0

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

                val entity = GuestModel(guestId, name, present)

                viewModel.save(entity)
            }
        }

        observe()

        loadData()
    }

    private fun observe() {
        viewModel.guest.observe(this) {
            binding.editName.setText(it.name)
            if (it.presence){
                binding.radioPresent.isChecked
            } else{
                binding.radioAbsent.isChecked
            }
        }

        viewModel.saveGuest.observe(this){
            if (it != ""){
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null){
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}