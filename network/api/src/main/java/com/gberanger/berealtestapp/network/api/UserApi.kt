package com.gberanger.berealtestapp.network.api

import com.gberanger.berealtestapp.network.api.models.UserApiModel

interface UserApi {
    suspend fun getUser(accessToken: String): UserApiModel
}