package com.gberanger.berealtestapp.session.domain.use_cases

interface SessionGetAccessTokenUseCase {
    suspend operator fun invoke(): String
}