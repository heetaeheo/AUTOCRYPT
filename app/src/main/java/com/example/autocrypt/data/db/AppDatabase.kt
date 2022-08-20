package com.example.autocrypt.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CenterDataEntity::class], version = 1, exportSchema = false )
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAppDao() : AppDao

    companion object{
        const val DB_NAME = "MYDB"
    }


}