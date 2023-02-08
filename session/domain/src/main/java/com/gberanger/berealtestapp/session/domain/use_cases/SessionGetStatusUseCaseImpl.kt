package com.gberanger.berealtestapp.session.domain.use_cases

import com.gberanger.berealtestapp.common.di.IoDispatcher
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel
import com.gberanger.berealtestapp.session.domain.repositories.SessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionGetStatusUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SessionGetStatusUseCase {

    override suspend fun invoke(): SessionStatusDomainModel =
        withContext(ioDispatcher) {
            sessionRepository.getSessionStatus()
        }
}