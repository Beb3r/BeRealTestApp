package com.gberanger.berealtestapp.browser.domain.repositories

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import kotlinx.coroutines.flow.Flow
interface BrowserRepository {
    suspend fun fetchItemById(id: String, rootFolder: Boolean)
    suspend fun observeItemsById(id: String): Flow<List<BrowserItemDomainModel>>
    suspend fun deleteItemById(id: String)
    suspend fun clearData()
}