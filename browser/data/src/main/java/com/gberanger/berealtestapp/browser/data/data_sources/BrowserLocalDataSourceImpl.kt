package com.gberanger.berealtestapp.browser.data.data_sources

import com.gberanger.berealtestapp.browser.data.mappers.toDomainModel
import com.gberanger.berealtestapp.browser.data.mappers.toEntityModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.persistence.dao.ItemDao
import kotlinx.coroutines.flow.*
import javax.inject.Inject
class BrowserLocalDataSourceImpl @Inject constructor(
    private val itemDao: ItemDao
) : BrowserLocalDataSource {
    override suspend fun saveItems(items: List<BrowserItemDomainModel>, rootFolder: Boolean) =
        itemDao.addItems(items.map { it.toEntityModel() }, rootFolder)
    override suspend fun observeItemsById(id: String): Flow<List<BrowserItemDomainModel>> =
        flow {
            emitAll(
                itemDao.observeItemsByParentId(id)
                    .distinctUntilChanged()
                    .map { items ->
                        items.map { it.toDomainModel() }
                    }
            )
        }
    override suspend fun deleteItemById(id: String) =
        itemDao.deleteItemById(id)
    override suspend fun clearData() =
        itemDao.deleteItems()
}