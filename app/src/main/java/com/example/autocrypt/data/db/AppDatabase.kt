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
//        private var DB_INSTANCE: AppDatabase? =null
//
//        fun getAppDbInstance(context: Context): AppDatabase{
//            if(DB_INSTANCE == null){
//                DB_INSTANCE = Room.databaseBuilder<AppDatabase>(
//                    context.applicationContext, AppDatabase::class.java,"MYDB"
//                )
//                    .allowMainThreadQueries()
//                    .build()
//            }
//            return DB_INSTANCE!!
//        }
//
//        fun destroyInstance(){
//            DB_INSTANCE = null
//        }
    }


}