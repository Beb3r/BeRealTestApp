package com.gberanger.berealtestapp.browser.domain.use_cases

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import kotlinx.coroutines.flow.Flow

interface BrowserObserveItemsByIdUseCase {
    suspend operator fun invoke(id: String): Flow<List<BrowserItemDomainModel>>
}