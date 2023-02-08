package com.gberanger.berealtestapp.browser.data.repositories

import com.gberanger.berealtestapp.browser.data.data_sources.BrowserLocalDataSource
import com.gberanger.berealtestapp.browser.data.data_sources.BrowserRemoteDataSource
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.repositories.BrowserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BrowserRepositoryImpl @Inject constructor(
    private val browserRemoteDataSource: BrowserRemoteDataSource,
    private val browserLocalDataSource: BrowserLocalDataSource
) : BrowserRepository {
    override suspend fun fetchItemById(id: String) {
        val items = browserRemoteDataSource.fetchItemById(id)
        browserLocalDataSource.saveItems(items)
    }
    override suspend fun observeItemsById(id: String): Flow<List<BrowserItemDomainModel>> =
        browserLocalDataSource.observeItemsById(id)

    override suspend fun clearData() =
        browserLocalDataSource.clearData()
}