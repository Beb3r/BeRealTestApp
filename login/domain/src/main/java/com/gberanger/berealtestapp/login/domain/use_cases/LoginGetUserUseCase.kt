package com.gberanger.berealtestapp.login.domain.use_cases

import com.gberanger.berealtestapp.common.Result
import com.gberanger.berealtestapp.login.domain.models.LoginUserCredentialsDomainModel
import com.gberanger.berealtestapp.login.domain.models.LoginUserDataDomainModel


interface LoginGetUserUseCase {

    suspend operator fun invoke(credentials: LoginUserCredentialsDomainModel): Result<LoginUserDataDomainModel>
}