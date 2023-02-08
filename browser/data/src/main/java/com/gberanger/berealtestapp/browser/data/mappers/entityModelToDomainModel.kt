package com.gberanger.berealtestapp.browser.data.mappers

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemTypeDomainModel
import com.gberanger.berealtestapp.persistence.dao.models.ItemEntityModel

fun ItemEntityModel.toDomainModel(): BrowserItemDomainModel =
    BrowserItemDomainModel(
        id = this.id,
        parentId = this.parentId ?: BrowserItemDomainModel.DEFAULT_PARENT_ID,
        name = this.name ?: BrowserItemDomainModel.DEFAULT_NAME,
        modificationDate = this.modificationDate?: BrowserItemDomainModel.DEFAULT_MODIFICATION_DATE,
        size = this.size?: BrowserItemDomainModel.DEFAULT_SIZE,
        type = this.type?.toBrowserItemTypeDomainModel() ?: BrowserItemTypeDomainModel.UNKNOWN
    )
private fun Int.toBrowserItemTypeDomainModel(): BrowserItemTypeDomainModel =
    when(this) {
        1 -> BrowserItemTypeDomainModel.FOLDER
        2 ->BrowserItemTypeDomainModel.FILE_IMAGE
        3 ->BrowserItemTypeDomainModel.FILE_TEXT
        else -> BrowserItemTypeDomainModel.UNKNOWN
    }