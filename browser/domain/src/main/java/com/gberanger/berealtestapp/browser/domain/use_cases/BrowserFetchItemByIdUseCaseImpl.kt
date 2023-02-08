package com.gberanger.berealtestapp.browser.domain.use_cases

import com.gberanger.berealtestapp.browser.domain.repositories.BrowserRepository
import com.gberanger.berealtestapp.common.Result
import com.gberanger.berealtestapp.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class BrowserFetchItemByIdUseCaseImpl @Inject constructor(
    private val browserRepository: BrowserRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BrowserFetchItemByIdUseCase {

    override suspend fun invoke(id: String): Result<Any> {
        return try {
            withContext(ioDispatcher) {
                browserRepository.fetchItemById(id)
                Result.Success(Unit)
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.Error(e)
        }
    }
}