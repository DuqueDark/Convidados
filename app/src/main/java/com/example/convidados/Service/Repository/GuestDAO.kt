package com.example.convidados.Service.Repository

import androidx.room.*
import com.example.convidados.Service.Constants.DataBaseConstants
import com.example.convidados.Service.Model.GuestModel

@Dao
interface GuestDAO{

    @Insert
    fun save(Guest: GuestModel): Long

    @Update
    fun update(Guest: GuestModel): Int

    @Delete
    fun delete(Guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int):GuestModel

    @Query("SELECT * FROM Guest  ORDER BY name")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1 ORDER BY name")
    fun getPresence(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0 ORDER BY name")
    fun getAbsents(): List<GuestModel>


}