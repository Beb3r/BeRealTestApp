package com.gberanger.berealtestapp.login.data.data_sources

import com.gberanger.berealtestapp.login.domain.models.LoginUserDataDomainModel

interface LoginRemoteDataSource {
    suspend fun getUser(accessToken: String): LoginUserDataDomainModel
}