package com.example.convidados.View.ViewHolder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.Service.Model.GuestModel
import com.example.convidados.View.Listener.GuestListener

class GuestViewHolder(view: View,private val listener: GuestListener): RecyclerView.ViewHolder(view) {

    fun bind(guest:GuestModel){
        val texte = itemView.findViewById<TextView>(R.id.text_layout_recycler)
        texte.text = guest.name

        texte.setOnClickListener {
            listener.onClick(guest.id)
        }

        texte.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover){ _, _ ->
                    listener.onDelete(guest)
                }
                .setNeutralButton(R.string.cancelar,null)
                .show()


            true
        }
    }
}