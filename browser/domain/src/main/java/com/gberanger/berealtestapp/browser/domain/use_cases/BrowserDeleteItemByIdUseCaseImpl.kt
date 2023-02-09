package com.gberanger.berealtestapp.browser.domain.use_cases

import com.gberanger.berealtestapp.browser.domain.repositories.BrowserRepository
import com.gberanger.berealtestapp.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
class BrowserDeleteItemByIdUseCaseImpl @Inject constructor(
    private val browserRepository: BrowserRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BrowserDeleteItemByIdUseCase {
    override suspend fun invoke(id: String) =
        try {
            withContext(ioDispatcher) {
                browserRepository.deleteItemById(id)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
}