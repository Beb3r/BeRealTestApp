package com.gberanger.berealtestapp.login.domain.models

data class LoginUserDataDomainModel(
    val firstName: String,
    val lastName: String,
    val rootItemId: String,
    val rootItemName: String
) {
    companion object {
        const val DEFAULT_FIRST_NAME = ""
        const val DEFAULT_LAST_NAME = ""
        const val DEFAULT_ROOT_ITEM_ID = ""
        const val DEFAULT_ROOT_ITEM_NAME = ""

        val DEFAULT = LoginUserDataDomainModel(
            firstName = DEFAULT_FIRST_NAME,
            lastName = DEFAULT_LAST_NAME,
            rootItemId = DEFAULT_ROOT_ITEM_ID,
            rootItemName = DEFAULT_ROOT_ITEM_NAME
        )
    }
}