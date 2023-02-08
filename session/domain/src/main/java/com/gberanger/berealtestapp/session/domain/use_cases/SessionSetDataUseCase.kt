package com.gberanger.berealtestapp.session.domain.use_cases

import com.gberanger.berealtestapp.common.Result
import com.gberanger.berealtestapp.session.domain.models.SessionDataDomainModel

interface SessionSetDataUseCase {
    suspend operator fun invoke(data: SessionDataDomainModel): Result<Any>
}