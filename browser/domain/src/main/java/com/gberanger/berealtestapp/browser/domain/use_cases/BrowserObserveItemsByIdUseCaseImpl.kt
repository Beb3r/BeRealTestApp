package com.gberanger.berealtestapp.browser.domain.use_cases

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.repositories.BrowserRepository
import com.gberanger.berealtestapp.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BrowserObserveItemsByIdUseCaseImpl @Inject constructor(
    private val browserRepository: BrowserRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BrowserObserveItemsByIdUseCase {

    override suspend fun invoke(id: String): Flow<List<BrowserItemDomainModel>> =
        browserRepository.observeItemsById(id).flowOn(ioDispatcher)
}