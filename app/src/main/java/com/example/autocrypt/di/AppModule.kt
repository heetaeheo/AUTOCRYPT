package com.example.autocrypt.di

import android.app.Application
import com.example.autocrypt.data.db.AppDao
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
    fun getAppDatabase(context: Application): AppDatabase{
        return AppDatabase.getAppDbInstance(context)
    }

    @Singleton
    @Provides
    fun appDao(appDatabase: AppDatabase) : AppDao {
        return appDatabase.getAppDao()
    }

//    @Provides
//    @Singleton
//    fun providesRemoteDao(database: Database) =
//        database.remoteKeyDao()
//
//    @Provides
//    @Singleton
//    fun providesDatabase(context:Application) =
//        Room.databaseBuilder(context,Database::class.java,"Database")
//            .build()
//
//    @Provides
//    @Singleton
//    fun providesDao(database: Database) =
//        database.getDao()
//

}