package com.gberanger.berealtestapp.login.data.repositories

import com.gberanger.berealtestapp.login.data.data_sources.LoginRemoteDataSource
import com.gberanger.berealtestapp.login.domain.models.LoginUserDataDomainModel
import com.gberanger.berealtestapp.login.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginRemoteDataSource
): LoginRepository {

    override suspend fun getUser(accessToken: String): LoginUserDataDomainModel =
        dataSource.getUser(accessToken)
}