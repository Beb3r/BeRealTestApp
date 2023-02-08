package com.gberanger.berealtestapp.network.api.models

import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    val firstName: String? = null,
    val lastName: String? = null,
    val rootItem: ItemApiModel? = null
)