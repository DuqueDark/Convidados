package com.example.convidados.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.Service.Model.GuestModel
import com.example.convidados.Service.Repository.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest:MutableLiveData<Boolean> = mSaveGuest

    fun save(name: String, presence:Boolean){
        val guest = GuestModel(name = name,presence = presence)
        saveGuest.value = mGuestRepository.save(guest)
    }
}