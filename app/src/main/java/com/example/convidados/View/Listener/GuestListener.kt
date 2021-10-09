package com.example.convidados.View.Listener

import com.example.convidados.Service.Model.GuestModel

interface GuestListener {
    fun onClick(id: Int)
    fun onDelete(guest:GuestModel)
}