package com.company.convidades.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.convidades.R
import com.company.convidades.databinding.RowGuestBinding
import com.company.convidades.model.GuestModel
import com.company.convidades.view.listener.IGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: IGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guestModel: GuestModel) {
        bind.textName.text = guestModel.name

        bind.textName.setOnClickListener {
            listener.onClick(guestModel.id)
        }

        bind.textName.setOnLongClickListener {
            listener.onDelete(guestModel.id)
            true
        }
    }

}