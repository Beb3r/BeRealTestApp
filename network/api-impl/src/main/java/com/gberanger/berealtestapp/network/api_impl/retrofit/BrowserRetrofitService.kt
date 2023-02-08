package com.gberanger.berealtestapp.network.api_impl.retrofit

import com.gberanger.berealtestapp.network.api.models.ItemApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface BrowserRetrofitService {
    companion object {
        private const val PATH_ID = "id"
    }
    @GET("items/{$PATH_ID}")
    suspend fun getItem(
        @Path(PATH_ID) id: String
    ): List<ItemApiModel>
}