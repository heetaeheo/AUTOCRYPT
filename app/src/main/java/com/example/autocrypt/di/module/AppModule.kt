package com.example.autocrypt.di.module

import android.app.Application
import androidx.room.Room
import com.example.autocrypt.data.db.dao.AppDao
import com.example.autocrypt.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java, AppDatabase.DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun appDao(appDatabase: AppDatabase) : AppDao {
        return appDatabase.getAppDao()
    }

}