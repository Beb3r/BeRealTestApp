package com.gberanger.berealtestapp.browser.domain.use_cases

interface BrowserDeleteItemByIdUseCase {

    suspend operator fun invoke(id: String)
}