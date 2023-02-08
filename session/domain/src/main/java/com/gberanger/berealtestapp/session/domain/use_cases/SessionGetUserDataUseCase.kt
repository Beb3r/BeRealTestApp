package com.gberanger.berealtestapp.session.domain.use_cases

import com.gberanger.berealtestapp.session.domain.models.SessionUserDataDomainModel

interface SessionGetUserDataUseCase {
    suspend operator fun invoke(): SessionUserDataDomainModel
}