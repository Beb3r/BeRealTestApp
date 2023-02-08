package com.gberanger.berealtestapp.login.data.di

import com.gberanger.berealtestapp.login.domain.use_cases.LoginGetUserUseCase
import com.gberanger.berealtestapp.login.domain.use_cases.LoginGetUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface LoginViewModelModule {

    @Binds
    fun bindLoginGetUserUseCase(impl: LoginGetUserUseCaseImpl): LoginGetUserUseCase
}