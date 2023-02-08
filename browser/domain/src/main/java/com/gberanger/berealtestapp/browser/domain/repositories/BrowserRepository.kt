package com.gberanger.berealtestapp.browser.domain.repositories

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import kotlinx.coroutines.flow.Flow
interface BrowserRepository {
    suspend fun fetchItemById(id: String)
    suspend fun observeItemsById(id: String): Flow<List<BrowserItemDomainModel>>

    suspend fun clearData()
}