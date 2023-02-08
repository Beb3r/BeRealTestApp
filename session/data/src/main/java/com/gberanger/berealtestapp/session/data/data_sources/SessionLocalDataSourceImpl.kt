package com.gberanger.berealtestapp.session.data.data_sources

import android.content.SharedPreferences
import com.gberanger.berealtestapp.session.domain.models.SessionDataDomainModel
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

class SessionLocalDataSourceImpl @Inject constructor(
    @Named("session") private val sharedPreferences: SharedPreferences,
) : SessionLocalDataSource {
    companion object {
        private const val KEY_FIRST_NAME = "99cc79cf-a9b1-435e-9d25-1020b5ce91b6"
        private const val KEY_LAST_NAME = "5d4a5796-d451-40ba-a6f2-09bf08ec4254"
        private const val KEY_ACCESS_TOKEN = "bc6069cc-7247-4145-ad38-cfe4035c2037"
        private const val KEY_ROOT_ITEM_ID = "d0a0827b-231e-4063-bf8b-25a6c2430ca5"
        private const val KEY_ROOT_ITEM_NAME = "1591b36f-376f-4f9d-9e0d-dc937967d5a1"
    }

    override suspend fun setData(data: SessionDataDomainModel) {
        sharedPreferences.edit()
            .putString(KEY_FIRST_NAME, data.firstName)
            .putString(KEY_LAST_NAME, data.lastName)
            .putString(KEY_ACCESS_TOKEN, data.accessToken)
            .putString(KEY_ROOT_ITEM_ID, data.rootItemId)
            .putString(KEY_ROOT_ITEM_NAME, data.rootItemName)
            .apply()

    }

    override suspend fun getSessionStatus(): SessionStatusDomainModel =
        if (sharedPreferences.getString(KEY_ACCESS_TOKEN, "").isNullOrBlank()) {
            SessionStatusDomainModel.LOGGED_OUT
        } else {
            SessionStatusDomainModel.LOGGED_IN
        }
}