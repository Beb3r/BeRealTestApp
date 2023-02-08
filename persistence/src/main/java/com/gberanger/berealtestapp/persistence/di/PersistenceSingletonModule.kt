package com.gberanger.berealtestapp.persistence.di

import android.content.Context
import androidx.room.Room
import com.gberanger.berealtestapp.persistence.PersistenceDatabase
import com.gberanger.berealtestapp.persistence.dao.ItemDao
import com.gberanger.berealtestapp.persistence.BuildConfig.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceSingletonModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): PersistenceDatabase =
        Room
            .databaseBuilder(appContext, PersistenceDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()

    @Provides
    fun provideItemDao(database: PersistenceDatabase): ItemDao =
        database.itemDao()
}
