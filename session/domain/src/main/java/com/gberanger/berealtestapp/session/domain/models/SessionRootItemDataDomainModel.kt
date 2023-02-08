package com.gberanger.berealtestapp.session.domain.models

data class SessionRootItemDataDomainModel(
    val rootItemId: String,
    val rootItemName: String
) {
    companion object {
        const val DEFAULT_ID = ""
        const val DEFAULT_NAME = ""
    }
}