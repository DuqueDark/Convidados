package com.example.convidados.Service.Repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.Service.Constants.DataBaseConstants
import com.example.convidados.Service.Model.GuestModel

class GuestRepository(context: Context){

    private val mDataBase = GuestDataBase.getDataBase(context).guestDAO()

    fun save(guest: GuestModel):Boolean{
        return mDataBase.save(guest) > 0
    }

    fun get(id: Int): GuestModel?{
        return mDataBase.get(id)
    }

    fun gutAll():List<GuestModel>{
        return mDataBase.getAll()
    }

    fun getPresence():List<GuestModel>{
        return mDataBase.getPresence()
    }

    fun getAbsent():List<GuestModel>{
        return  mDataBase.getAbsents()
    }

    fun update(guest: GuestModel) : Boolean{
        return  mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel){
        mDataBase.delete(guest)
    }
}