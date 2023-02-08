package com.gberanger.berealtestapp.browser.domain.models

data class BrowserItemDomainModel(
    val id: String,
    val parentId: String,
    val name: String,
    val modificationDate: String,
    val size: Int,
    val type: BrowserItemTypeDomainModel
) {
    companion object {
        const val DEFAULT_ID = ""
        const val DEFAULT_PARENT_ID = ""
        const val DEFAULT_NAME = ""
        const val DEFAULT_MODIFICATION_DATE = ""
        const val DEFAULT_SIZE = -1
    }
}