package com.gberanger.berealtestapp.session.data.data_sources

import com.gberanger.berealtestapp.session.domain.models.SessionDataDomainModel
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel

interface SessionLocalDataSource {
    suspend fun setData(data: SessionDataDomainModel)
    suspend fun getSessionStatus(): SessionStatusDomainModel
}