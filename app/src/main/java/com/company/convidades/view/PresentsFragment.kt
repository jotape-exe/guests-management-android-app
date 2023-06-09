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
import com.company.convidades.databinding.FragmentPresentsBinding
import com.company.convidades.view.adapter.GuestsAdapter
import com.company.convidades.view.listener.IGuestListener
import com.company.convidades.viewmodel.GuestsViewModel

private const val PRESENT_PRESENCE = 1

class PresentsFragment : Fragment() {

    private var _binding: FragmentPresentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = GuestsAdapter()
    private lateinit var guestsViewModel: GuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        guestsViewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentPresentsBinding.inflate(inflater, container, false)

        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerGuests.adapter = adapter

        val listerner = object : IGuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                guestsViewModel.delete(id)
                guestsViewModel.getByPresence(PRESENT_PRESENCE)
            }

        }

        adapter.attachListener(listerner)

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        guestsViewModel.getByPresence(PRESENT_PRESENCE)
    }

    private fun observe() {
        guestsViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}