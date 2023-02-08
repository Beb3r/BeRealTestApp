package com.gberanger.berealtestapp.session.domain.use_cases

import com.gberanger.berealtestapp.common.di.IoDispatcher
import com.gberanger.berealtestapp.session.domain.repositories.SessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionGetAccessTokenUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SessionGetAccessTokenUseCase {

    override suspend fun invoke(): String =
        withContext(ioDispatcher) {
            sessionRepository.getAccessToken()
        }
}
