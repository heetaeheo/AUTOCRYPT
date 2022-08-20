package com.example.autocrypt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.autocrypt.data.entity.CenterDataEntity

@Database(entities = [CenterDataEntity::class], version = 1, exportSchema = false )
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAppDao() : AppDao

    companion object{
        const val DB_NAME = "MYDB"
    }


}