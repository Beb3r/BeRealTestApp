package com.gberanger.berealtestapp.browser.data.data_sources

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel

interface BrowserRemoteDataSource {
    suspend fun fetchItemById(id: String): List<BrowserItemDomainModel>

    suspend fun deleteItemById(id: String)
}