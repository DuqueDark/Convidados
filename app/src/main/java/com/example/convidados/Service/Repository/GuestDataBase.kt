package com.example.convidados.Service.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.convidados.Service.Constants.DataBaseConstants
import com.example.convidados.Service.Model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase: RoomDatabase(){

    abstract fun guestDAO():GuestDAO

    companion object{
        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context):GuestDataBase{

            if(!::INSTANCE.isInitialized){
                synchronized(GuestDataBase::class){
                    INSTANCE = Room.databaseBuilder(context,GuestDataBase::class.java,DataBaseConstants.GUEST.DATA_BASE_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}