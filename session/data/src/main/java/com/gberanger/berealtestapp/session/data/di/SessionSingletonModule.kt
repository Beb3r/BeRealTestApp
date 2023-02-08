package com.gberanger.berealtestapp.session.data.di

import android.content.Context
import android.content.SharedPreferences
import com.gberanger.berealtestapp.session.data.data_sources.SessionLocalDataSource
import com.gberanger.berealtestapp.session.data.data_sources.SessionLocalDataSourceImpl
import com.gberanger.berealtestapp.session.data.repositories.SessionRepositoryImpl
import com.gberanger.berealtestapp.session.domain.repositories.SessionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SessionSingletonModule {

    @Binds
    @Singleton
    fun bindSessionRepository(impl: SessionRepositoryImpl): SessionRepository

    @Binds
    fun bindSessionLocalDataSource(impl: SessionLocalDataSourceImpl): SessionLocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object SessionSingletonModuleProvider {

    @Singleton
    @Provides
    @Named("session")
    fun provideAuthSharedPrefs(
        @ApplicationContext context: Context,
    ): SharedPreferences {
        return context.getSharedPreferences(
            "f03894da-5693-43a3-9e02-d51cd230cb46",
            Context.MODE_PRIVATE
        )
    }
}