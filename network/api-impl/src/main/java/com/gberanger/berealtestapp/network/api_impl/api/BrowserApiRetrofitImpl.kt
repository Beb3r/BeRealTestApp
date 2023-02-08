package com.gberanger.berealtestapp.network.api_impl.api

import com.gberanger.berealtestapp.network.api.BrowserApi
import com.gberanger.berealtestapp.network.api.models.ItemApiModel
import com.gberanger.berealtestapp.network.api_impl.extensions.createService
import com.gberanger.berealtestapp.network.api_impl.qualifiers.LoggedInQualifier
import com.gberanger.berealtestapp.network.api_impl.retrofit.BrowserRetrofitService
import retrofit2.Retrofit
import javax.inject.Inject

class BrowserApiRetrofitImpl @Inject constructor(
    @LoggedInQualifier retrofit: Retrofit
) : BrowserApi {

    private val service: BrowserRetrofitService by retrofit.createService()

    override suspend fun getItem(id: String): List<ItemApiModel> =
        service.getItem(id)
}