package com.gberanger.berealtestapp.browser.domain.use_cases

import com.gberanger.berealtestapp.common.Result

interface BrowserFetchItemByIdUseCase {
    suspend operator fun invoke(id: String): Result<Any>
}