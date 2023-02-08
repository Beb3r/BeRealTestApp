package com.gberanger.berealtestapp.session.domain.use_cases

import com.gberanger.berealtestapp.session.domain.models.SessionRootItemDataDomainModel

interface SessionGetRootItemDataUseCase {

    suspend operator fun invoke(): SessionRootItemDataDomainModel
}