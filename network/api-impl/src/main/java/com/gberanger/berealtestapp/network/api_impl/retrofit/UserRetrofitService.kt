package com.gberanger.berealtestapp.network.api_impl.retrofit

import com.gberanger.berealtestapp.network.api.models.UserApiModel
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface UserRetrofitService {

    companion object {
        private const val PATH_USER = "me"
    }

    @GET(PATH_USER)
    suspend fun getUser(
        @HeaderMap headers: Map<String, String>
    ): UserApiModel

}