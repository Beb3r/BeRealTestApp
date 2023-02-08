package com.gberanger.berealtestapp.network.okhttp

import com.gberanger.berealtestapp.session.domain.use_cases.SessionGetAccessTokenUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(
    private val getAccessTokenUseCase: SessionGetAccessTokenUseCase,
) : Interceptor {

    companion object {
        const val AUTH_HEADER_KEY = "Authorization"
        private const val AUTH_HEADER_VALUE = "Basic %1\$s"

        fun getAuthHeader(accessToken: String): String =
            String.format(AUTH_HEADER_VALUE, accessToken)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking {
            getAccessTokenUseCase.invoke()
        }

        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .apply {
                    header(AUTH_HEADER_KEY, getAuthHeader(accessToken))
                }.build()
        )
    }

}
