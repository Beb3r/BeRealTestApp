package com.gberanger.berealtestapp.browser.data.data_sources

import com.gberanger.berealtestapp.browser.data.mappers.toDomainModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.network.api.BrowserApi
import javax.inject.Inject

class BrowserRemoteDataSourceImpl @Inject constructor(
    private val browserApi: BrowserApi
) : BrowserRemoteDataSource {
    override suspend fun fetchItemById(id: String): List<BrowserItemDomainModel> =
        browserApi.getItem(id).map {
            it.toDomainModel()
        }
}