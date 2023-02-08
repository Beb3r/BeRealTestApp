package com.gberanger.berealtestapp.browser.data.mappers

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemTypeDomainModel
import com.gberanger.berealtestapp.persistence.dao.models.ItemEntityModel

fun BrowserItemDomainModel.toEntityModel(): ItemEntityModel =
    ItemEntityModel(
        id = this.id,
        parentId = this.parentId,
        name = this.name,
        modificationDate = this.modificationDate,
        size = this.size,
        type = this.type.toInt()
    )
fun BrowserItemTypeDomainModel.toInt(): Int =
    when(this) {
        BrowserItemTypeDomainModel.FOLDER -> 1
        BrowserItemTypeDomainModel.FILE_IMAGE -> 2
        BrowserItemTypeDomainModel.FILE_TEXT -> 3
        else -> 0
    }