package com.gberanger.berealtestapp.session.data.di

import com.gberanger.berealtestapp.session.domain.use_cases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface SessionViewModelModule {
    @Binds
    fun bindSessionSetDataUseCase(impl: SessionSetDataUseCaseImpl): SessionSetDataUseCase
    @Binds
    fun bindSessionGetStatusUseCase(impl: SessionGetStatusUseCaseImpl): SessionGetStatusUseCase

    @Binds
    fun bindSessionGetRootItemDataUseCase(impl: SessionGetRootItemDataUseCaseImpl): SessionGetRootItemDataUseCase
}