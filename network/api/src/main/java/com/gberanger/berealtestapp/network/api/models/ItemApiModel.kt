package com.gberanger.berealtestapp.network.api.models

import kotlinx.serialization.Serializable

@Serializable
data class ItemApiModel(
    val contentType: String? = null,
    val id: String? = null,
    val isDir: Boolean? = null,
    val modificationDate: String? = null,
    val name: String? = null,
    val parentId: String? = null,
    val size: Int? = null
)