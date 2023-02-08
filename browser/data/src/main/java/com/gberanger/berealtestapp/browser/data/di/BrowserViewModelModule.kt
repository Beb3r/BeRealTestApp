package com.gberanger.berealtestapp.browser.data.di

import com.gberanger.berealtestapp.browser.domain.use_cases.BrowserFetchItemByIdUseCase
import com.gberanger.berealtestapp.browser.domain.use_cases.BrowserFetchItemByIdUseCaseImpl
import com.gberanger.berealtestapp.browser.domain.use_cases.BrowserObserveItemsByIdUseCase
import com.gberanger.berealtestapp.browser.domain.use_cases.BrowserObserveItemsByIdUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface BrowserViewModelModule {
    @Binds
    fun bindBrowserFetchItemByIdUseCase(impl: BrowserFetchItemByIdUseCaseImpl): BrowserFetchItemByIdUseCase

    @Binds
    fun bindBrowserObserveItemsByIdUseCase(impl: BrowserObserveItemsByIdUseCaseImpl): BrowserObserveItemsByIdUseCase

}