package com.gberanger.berealtestapp.session.domain.use_cases

import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel

interface SessionGetStatusUseCase {
    suspend operator fun invoke(): SessionStatusDomainModel
}