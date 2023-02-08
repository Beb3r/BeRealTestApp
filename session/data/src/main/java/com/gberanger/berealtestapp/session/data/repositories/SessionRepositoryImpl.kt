package com.gberanger.berealtestapp.session.data.repositories

import com.gberanger.berealtestapp.session.data.data_sources.SessionLocalDataSource
import com.gberanger.berealtestapp.session.domain.models.SessionDataDomainModel
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel
import com.gberanger.berealtestapp.session.domain.repositories.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionLocalDataSource: SessionLocalDataSource
) : SessionRepository {
    override suspend fun setData(data: SessionDataDomainModel) =
        sessionLocalDataSource.setData(data)

    override suspend fun getSessionStatus(): SessionStatusDomainModel =
        sessionLocalDataSource.getSessionStatus()
}