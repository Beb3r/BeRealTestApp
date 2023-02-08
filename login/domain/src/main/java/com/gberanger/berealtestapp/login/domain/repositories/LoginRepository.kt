package com.gberanger.berealtestapp.login.domain.repositories

import com.gberanger.berealtestapp.login.domain.models.LoginUserDataDomainModel

interface LoginRepository {
    suspend fun getUser(accessToken: String): LoginUserDataDomainModel
}