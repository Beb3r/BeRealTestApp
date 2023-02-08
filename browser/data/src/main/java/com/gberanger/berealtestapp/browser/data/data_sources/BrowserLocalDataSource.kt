package com.gberanger.berealtestapp.browser.data.data_sources

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import kotlinx.coroutines.flow.Flow

interface BrowserLocalDataSource {
    suspend fun saveItems(items: List<BrowserItemDomainModel>)
    suspend fun observeItemsById(id: String): Flow<List<BrowserItemDomainModel>>

    suspend fun clearData()
}