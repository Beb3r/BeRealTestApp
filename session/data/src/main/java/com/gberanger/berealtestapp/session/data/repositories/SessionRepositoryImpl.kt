package com.gberanger.berealtestapp.session.data.repositories

import com.gberanger.berealtestapp.session.data.data_sources.SessionLocalDataSource
import com.gberanger.berealtestapp.session.domain.models.SessionDataDomainModel
import com.gberanger.berealtestapp.session.domain.models.SessionRootItemDataDomainModel
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel
import com.gberanger.berealtestapp.session.domain.models.SessionUserDataDomainModel
import com.gberanger.berealtestapp.session.domain.repositories.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionLocalDataSource: SessionLocalDataSource
) : SessionRepository {
    override suspend fun setData(data: SessionDataDomainModel) =
        sessionLocalDataSource.setData(data)

    override suspend fun getSessionStatus(): SessionStatusDomainModel =
        sessionLocalDataSource.getSessionStatus()

    override suspend fun getRootItemData(): SessionRootItemDataDomainModel =
        sessionLocalDataSource.getRootItemData()

    override suspend fun getAccessToken(): String =
        sessionLocalDataSource.getAccessToken()

    override suspend fun getUserData(): SessionUserDataDomainModel =
        sessionLocalDataSource.getUserData()

    override suspend fun clearData() =
        sessionLocalDataSource.clearData()
}