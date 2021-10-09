package com.example.convidados.Service.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.convidados.Service.Constants.DataBaseConstants
import com.example.convidados.Service.Constants.GuestConstants

@Entity(tableName = DataBaseConstants.GUEST.TABLE_NAME)
class GuestModel(){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DataBaseConstants.GUEST.COLUMNS.ID)
    var id:Int = 0

    @ColumnInfo(name = DataBaseConstants.GUEST.COLUMNS.NAME)
    var name:String = ""

    @ColumnInfo(name = DataBaseConstants.GUEST.COLUMNS.PRESENCE)
    var presence:Boolean = false
}
