package com.gberanger.berealtestapp.login.data.di

import com.gberanger.berealtestapp.login.data.data_sources.LoginRemoteDataSource
import com.gberanger.berealtestapp.login.data.data_sources.LoginRemoteDataSourceImpl
import com.gberanger.berealtestapp.login.data.repositories.LoginRepositoryImpl
import com.gberanger.berealtestapp.login.domain.repositories.LoginRepository
import com.gberanger.berealtestapp.login.domain.use_cases.LoginGetUserUseCase
import com.gberanger.berealtestapp.login.domain.use_cases.LoginGetUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoginSingletonModule {

    @Binds
    @Singleton
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindLoginRemoteDataSource(impl: LoginRemoteDataSourceImpl): LoginRemoteDataSource
}