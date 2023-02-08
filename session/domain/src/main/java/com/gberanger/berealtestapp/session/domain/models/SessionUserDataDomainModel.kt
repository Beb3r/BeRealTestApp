package com.gberanger.berealtestapp.session.domain.models

data class SessionUserDataDomainModel(
    val firstName: String,
    val lastName: String
) {
    companion object {
        const val DEFAULT_FIRST_NAME = ""
        const val DEFAULT_LAST_NAME = ""
    }
}