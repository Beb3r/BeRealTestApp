package com.gberanger.berealtestapp.session.domain.use_cases

import com.gberanger.berealtestapp.common.di.IoDispatcher
import com.gberanger.berealtestapp.session.domain.models.SessionRootItemDataDomainModel
import com.gberanger.berealtestapp.session.domain.repositories.SessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionGetRootItemDataUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SessionGetRootItemDataUseCase {

    override suspend fun invoke(): SessionRootItemDataDomainModel =
        withContext(ioDispatcher) {
            sessionRepository.getRootItemData()
        }
}