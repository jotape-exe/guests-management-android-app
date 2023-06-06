package com.company.convidades.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.convidades.R
import com.company.convidades.databinding.RowGuestBinding
import com.company.convidades.model.GuestModel

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guestModel: GuestModel) {
        bind.textName.text = guestModel.name
    }

}