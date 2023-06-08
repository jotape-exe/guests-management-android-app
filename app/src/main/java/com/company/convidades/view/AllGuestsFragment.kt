package com.company.convidades.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.convidades.constants.DataBaseConstants
import com.company.convidades.databinding.FragmentAllGuestsBinding
import com.company.convidades.view.adapter.GuestsAdapter
import com.company.convidades.view.listener.IGuestListener
import com.company.convidades.viewmodel.AllGuestsViewModel
import com.google.android.material.snackbar.Snackbar

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private val adapter = GuestsAdapter()
    private lateinit var allGuestsViewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        bundle: Bundle?
    ): View {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllGuests.adapter = adapter

        val listerner = object: IGuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle  = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                allGuestsViewModel.delete(id)
                allGuestsViewModel.finALl()
            }

        }

        adapter.attachListener(listerner)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        allGuestsViewModel.finALl()


    }

    private fun observe() {
        allGuestsViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}