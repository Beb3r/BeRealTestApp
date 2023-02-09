package com.gberanger.berealtestapp.network.di

import com.gberanger.berealtestapp.network.BuildConfig
import com.gberanger.berealtestapp.network.api_impl.qualifiers.JsonDefaultQualifier
import com.gberanger.berealtestapp.network.api_impl.qualifiers.LoggedInQualifier
import com.gberanger.berealtestapp.network.okhttp.OAuthInterceptor
import com.gberanger.berealtestapp.session.domain.use_cases.SessionGetAccessTokenUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkSingletonModule {
    @Singleton
    @Provides
    @com.gberanger.berealtestapp.network.api_impl.qualifiers.JsonDefaultQualifier
    @ExperimentalSerializationApi
    // Detailed documentation and examples:
    // https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md#json-configuration
    fun provideDefaultJson(): Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        explicitNulls = false
        coerceInputValues = true
    }
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Provides
    @ExperimentalSerializationApi
    fun provideRetrofit(
        @JsonDefaultQualifier json: Json,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }
    @Provides
    @LoggedInQualifier
    fun provideOAuthInterceptor(
        sessionGetAccessTokenUseCase: SessionGetAccessTokenUseCase
    ): Interceptor =
        OAuthInterceptor(sessionGetAccessTokenUseCase)
    @Provides
    @LoggedInQualifier
    fun provideLoggedInOkHttpClient(
        @LoggedInQualifier interceptor: Interceptor,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Provides
    @ExperimentalSerializationApi
    @LoggedInQualifier
    fun provideLoggedInRetrofit(
        @JsonDefaultQualifier json: Json,
        @LoggedInQualifier okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }
}