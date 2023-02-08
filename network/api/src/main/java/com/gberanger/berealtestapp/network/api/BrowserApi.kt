package com.gberanger.berealtestapp.network.api

import com.gberanger.berealtestapp.network.api.models.ItemApiModel

interface BrowserApi {
    suspend fun getItem(id: String): List<ItemApiModel>
}