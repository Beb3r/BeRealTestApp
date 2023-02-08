package com.gberanger.berealtestapp.browser.data.mappers

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemTypeDomainModel
import com.gberanger.berealtestapp.network.api.models.ItemApiModel

fun ItemApiModel.toDomainModel(): BrowserItemDomainModel =
    BrowserItemDomainModel(
        id = this.id ?: BrowserItemDomainModel.DEFAULT_ID,
        parentId = this.parentId ?: BrowserItemDomainModel.DEFAULT_PARENT_ID,
        name = this.name ?: BrowserItemDomainModel.DEFAULT_NAME,
        modificationDate = this.modificationDate ?: BrowserItemDomainModel.DEFAULT_MODIFICATION_DATE,
        size = this.size ?: BrowserItemDomainModel.DEFAULT_SIZE,
        type = toType(this.isDir == true, this.contentType)
    )

fun toType(isDir: Boolean, contentType: String?): BrowserItemTypeDomainModel =
    when {
        isDir -> BrowserItemTypeDomainModel.FOLDER
        contentType == "image/jpeg" -> BrowserItemTypeDomainModel.FILE_IMAGE
        contentType == "text/plain" -> BrowserItemTypeDomainModel.FILE_TEXT
        else -> BrowserItemTypeDomainModel.UNKNOWN
    }