package com.example.convidados.View.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.Service.Model.GuestModel

class GuestViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(guest:GuestModel){
        val texte = itemView.findViewById<TextView>(R.id.text_layout_recycler)
        texte.text = guest.name
    }
}