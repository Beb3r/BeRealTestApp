package com.gberanger.berealtestapp.login.data.data_sources

import com.gberanger.berealtestapp.login.data.mappers.toDomainModel
import com.gberanger.berealtestapp.login.domain.models.LoginUserDataDomainModel
import com.gberanger.berealtestapp.network.api.UserApi
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : LoginRemoteDataSource {

    override suspend fun getUser(accessToken: String): LoginUserDataDomainModel =
        userApi.getUser(accessToken).toDomainModel()
}