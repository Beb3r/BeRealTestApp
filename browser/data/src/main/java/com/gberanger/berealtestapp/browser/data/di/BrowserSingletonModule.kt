package com.gberanger.berealtestapp.browser.data.di

import com.gberanger.berealtestapp.browser.data.data_sources.BrowserLocalDataSource
import com.gberanger.berealtestapp.browser.data.data_sources.BrowserLocalDataSourceImpl
import com.gberanger.berealtestapp.browser.data.data_sources.BrowserRemoteDataSource
import com.gberanger.berealtestapp.browser.data.data_sources.BrowserRemoteDataSourceImpl
import com.gberanger.berealtestapp.browser.data.repositories.BrowserRepositoryImpl
import com.gberanger.berealtestapp.browser.domain.repositories.BrowserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BrowserSingletonModule {
    @Binds
    @Singleton
    fun bindBrowserRepository(impl: BrowserRepositoryImpl): BrowserRepository
    @Binds
    fun bindBrowserRemoteDataSource(impl: BrowserRemoteDataSourceImpl): BrowserRemoteDataSource

    @Binds
    fun bindBrowserLocalDataSource(impl: BrowserLocalDataSourceImpl): BrowserLocalDataSource
}