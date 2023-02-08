package com.gberanger.berealtestapp.network.api_impl.api

import com.gberanger.berealtestapp.network.api.UserApi
import com.gberanger.berealtestapp.network.api.models.UserApiModel
import com.gberanger.berealtestapp.network.api_impl.extensions.createService
import com.gberanger.berealtestapp.network.api_impl.retrofit.UserRetrofitService
import retrofit2.Retrofit
import javax.inject.Inject

class UserApiRetrofitImpl @Inject constructor(
    retrofit: Retrofit
) : UserApi {

    companion object {
        private fun getLoggedOutHeader(accessToken: String) =
            HashMap<String, String>().apply {
                put("Authorization", "Basic $accessToken")
            }
    }

    private val service: UserRetrofitService by retrofit.createService()

    override suspend fun getUser(accessToken: String): UserApiModel =
        service.getUser(getLoggedOutHeader(accessToken))


}