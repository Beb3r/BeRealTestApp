package com.gberanger.berealtestapp.network.api_impl.extensions

import retrofit2.Retrofit

inline fun <reified T : Any> Retrofit.createService(): Lazy<T> = lazy { create(T::class.java) }
