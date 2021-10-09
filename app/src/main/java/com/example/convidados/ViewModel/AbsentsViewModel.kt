package com.example.convidados.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.Service.Model.GuestModel
import com.example.convidados.Service.Repository.GuestRepository

class AbsentsViewModel (application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getInstance(application)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load() {
        mGuestList.value = mGuestRepository.getAbsent()
    }

    fun delete(id: Int) {
        mGuestRepository.delete(id)
    }
}