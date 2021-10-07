package com.example.convidados.Service.Repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.Service.Constants.DataBaseConstants
import com.example.convidados.Service.Model.GuestModel

class GuestRepository private constructor(context: Context){

    private var mGuestDataBaseHelp: GuestDataBaseHelp = GuestDataBaseHelp(context)

    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository{
            if(!::repository.isInitialized){
                repository = GuestRepository(context)
            }

            return repository
        }
    }

    fun save(guest: GuestModel):Boolean{
        val db = mGuestDataBaseHelp.writableDatabase
        val values = ContentValues()

        return try{

            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE,guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME,null,values)

            true
        }catch (e: Exception){
            false
        }
    }

    fun gutAll():List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList<GuestModel>()
        val db = mGuestDataBaseHelp.readableDatabase

        val columns = arrayOf(
            DataBaseConstants.GUEST.COLUMNS.ID,
            DataBaseConstants.GUEST.COLUMNS.NAME,
            DataBaseConstants.GUEST.COLUMNS.PRESENCE)

        return try{

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                DataBaseConstants.GUEST.COLUMNS.NAME
            )

            if(cursor.count > 0 && cursor != null){
                while (cursor.moveToNext()){

                    val gid = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val gname = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val gpresence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                     list.add(GuestModel(gid,gname,gpresence))
                }
            }

            cursor.close()

            list
        }catch (e: Exception){
            list
        }
    }

    fun getPresence():List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList<GuestModel>()
        val db = mGuestDataBaseHelp.readableDatabase

        val columns = arrayOf(
            DataBaseConstants.GUEST.COLUMNS.ID,
            DataBaseConstants.GUEST.COLUMNS.NAME,
            DataBaseConstants.GUEST.COLUMNS.PRESENCE)

        val selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
        val args = arrayOf("1")

        return try{

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                selection,
                args,
                null,
                null,
                DataBaseConstants.GUEST.COLUMNS.NAME
            )

            if(cursor.count > 0 && cursor != null){
                while (cursor.moveToNext()){

                    val gid = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val gname = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val gpresence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    list.add(GuestModel(gid,gname,gpresence))
                }
            }

            cursor.close()

            list
        }catch (e: Exception){
            list
        }
    }

    fun getAbsent():List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList<GuestModel>()
        val db = mGuestDataBaseHelp.readableDatabase

        val columns = arrayOf(
            DataBaseConstants.GUEST.COLUMNS.ID,
            DataBaseConstants.GUEST.COLUMNS.NAME,
            DataBaseConstants.GUEST.COLUMNS.PRESENCE)

        val selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
        val args = arrayOf("0")

        return try{

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                selection,
                args,
                null,
                null,
                DataBaseConstants.GUEST.COLUMNS.NAME
            )

            if(cursor.count > 0 && cursor != null){
                while (cursor.moveToNext()){

                    val gid = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val gname = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val gpresence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    list.add(GuestModel(gid,gname,gpresence))
                }
            }

            cursor.close()

            list
        }catch (e: Exception){
            list
        }
    }

    fun get(id: Int): GuestModel?{

        var guestModel: GuestModel? = null
        val db = mGuestDataBaseHelp.readableDatabase

        val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
        val args = arrayOf(id.toString())

        val columns = arrayOf(DataBaseConstants.GUEST.COLUMNS.ID,
            DataBaseConstants.GUEST.COLUMNS.NAME,
            DataBaseConstants.GUEST.COLUMNS.PRESENCE)

        return try{

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                selection,
                args,
                null,
                null,
                DataBaseConstants.GUEST.COLUMNS.ID
            )

            if(cursor.count > 0 && cursor != null){
                cursor.moveToFirst()
                val gid = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                val gname = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val gpresence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guestModel = GuestModel(gid,gname,gpresence)
            }

            cursor.close()

            guestModel
        }catch (e: Exception){
            guestModel
        }
    }


    fun update(guest: GuestModel) : Boolean{
        val db = mGuestDataBaseHelp.writableDatabase
        val values = ContentValues()

        val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
        val args = arrayOf(guest.id.toString())

        return try{

            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE,guest.presence)

            db.update(DataBaseConstants.GUEST.TABLE_NAME,values,selection, args)

            true
        }catch (e: Exception){
            false
        }
    }

    fun delete(id:Int):Boolean{
        val db = mGuestDataBaseHelp.writableDatabase

        val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
        val args = arrayOf(id.toString())

        return try{

            db.delete(DataBaseConstants.GUEST.TABLE_NAME,selection,args)

            true
        }catch (e: Exception){
            false
        }
    }

}