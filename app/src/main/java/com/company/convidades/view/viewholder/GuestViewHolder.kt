package com.company.convidades.view.viewholder

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
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

            AlertDialog.Builder(itemView.context)
                .setTitle("Atenção!")
                .setMessage("Tem certeza que deseja excluir?")
                .setPositiveButton(
                    "Sim"
                ) { dialog, which ->
                    listener.onDelete(guestModel.id)
                }
                .setNegativeButton("Não", null)
                .create().show()

            true
        }
    }

}