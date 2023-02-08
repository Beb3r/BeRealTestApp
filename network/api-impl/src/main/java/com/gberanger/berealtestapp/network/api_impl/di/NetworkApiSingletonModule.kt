package com.gberanger.berealtestapp.network.api_impl.di

import com.gberanger.berealtestapp.network.api.UserApi
import com.gberanger.berealtestapp.network.api_impl.api.UserApiRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkApiSingletonModule {

    @Binds
    fun bindUserApi(impl: UserApiRetrofitImpl): UserApi
}