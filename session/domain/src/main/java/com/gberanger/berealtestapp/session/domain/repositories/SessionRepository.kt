package com.gberanger.berealtestapp.session.domain.repositories

import com.gberanger.berealtestapp.session.domain.models.SessionDataDomainModel
import com.gberanger.berealtestapp.session.domain.models.SessionRootItemDataDomainModel
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel

interface SessionRepository {
    suspend fun setData(data: SessionDataDomainModel)
    suspend fun getSessionStatus(): SessionStatusDomainModel

    suspend fun getRootItemData(): SessionRootItemDataDomainModel

    suspend fun getAccessToken(): String
}