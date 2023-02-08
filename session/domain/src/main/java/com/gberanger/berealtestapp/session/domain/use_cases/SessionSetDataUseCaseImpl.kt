package com.gberanger.berealtestapp.session.domain.use_cases

import com.gberanger.berealtestapp.common.Result
import com.gberanger.berealtestapp.common.di.IoDispatcher
import com.gberanger.berealtestapp.session.domain.models.SessionDataDomainModel
import com.gberanger.berealtestapp.session.domain.repositories.SessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SessionSetDataUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): SessionSetDataUseCase {

    override suspend fun invoke(data: SessionDataDomainModel): Result<Any> {
        return try {
            withContext(ioDispatcher) {
                sessionRepository.setData(data)
                Result.Success(Unit)
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.Error(e)
        }
    }
}