package com.gberanger.berealtestapp.browser.domain.use_cases

import com.gberanger.berealtestapp.browser.domain.repositories.BrowserRepository
import com.gberanger.berealtestapp.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BrowserClearDataUseCaseImpl @Inject constructor(
    private val browserRepository: BrowserRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BrowserClearDataUseCase {

    override suspend fun invoke() =
        withContext(ioDispatcher) {
            browserRepository.clearData()
        }
}