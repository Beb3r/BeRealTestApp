package com.gberanger.berealtestapp.login.domain.use_cases

import com.gberanger.berealtestapp.common.di.IoDispatcher
import com.gberanger.berealtestapp.login.domain.models.LoginUserCredentialsDomainModel
import com.gberanger.berealtestapp.login.domain.models.LoginUserDataDomainModel
import com.gberanger.berealtestapp.login.domain.repositories.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.gberanger.berealtestapp.common.Result
import timber.log.Timber

class LoginGetUserUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : LoginGetUserUseCase {
    override suspend fun invoke(credentials: LoginUserCredentialsDomainModel): Result<LoginUserDataDomainModel> {
        return try {
            withContext(ioDispatcher) {
                val data = loginRepository.getUser(credentials.accessToken)
                Result.Success(data)
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.Error(e)
        }
    }
}