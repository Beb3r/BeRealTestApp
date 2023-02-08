package com.gberanger.berealtestapp.session.domain.models

data class SessionDataDomainModel(
    val firstName: String,
    val lastName: String,
    val accessToken: String,
    val rootItemId: String,
    val rootItemName: String
)