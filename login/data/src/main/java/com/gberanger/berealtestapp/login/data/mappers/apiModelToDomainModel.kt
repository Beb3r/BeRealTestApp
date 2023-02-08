package com.gberanger.berealtestapp.login.data.mappers

import com.gberanger.berealtestapp.login.domain.models.LoginUserDataDomainModel
import com.gberanger.berealtestapp.network.api.models.UserApiModel

fun UserApiModel.toDomainModel(): LoginUserDataDomainModel {
    val id = this.rootItem?.id
    if (id.isNullOrEmpty()) {
        throw IllegalStateException("Root item id must not be null or empty")
    }
    return LoginUserDataDomainModel(
        firstName = this.firstName ?: LoginUserDataDomainModel.DEFAULT_FIRST_NAME,
        lastName = this.lastName ?: LoginUserDataDomainModel.DEFAULT_LAST_NAME,
        rootItemId = this.rootItem?.id ?: LoginUserDataDomainModel.DEFAULT_ROOT_ITEM_ID,
        rootItemName = this.rootItem?.name ?: LoginUserDataDomainModel.DEFAULT_ROOT_ITEM_NAME
    )
}