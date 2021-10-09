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

    private val mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest:LiveData<Boolean> = mSaveGuest

    private val mGuest = MutableLiveData<GuestModel>()
    val loadGuest: LiveData<GuestModel> = mGuest

    fun save(id: Int, name: String, presence:Boolean){
        val guest = GuestModel(id,name,presence)

        if(id == 0){
            mSaveGuest.value = mGuestRepository.save(guest)
        }else
            mSaveGuest.value = mGuestRepository.update(guest)
    }

    fun load(id: Int) {
        mGuest.value = mGuestRepository.get(id)
    }
}