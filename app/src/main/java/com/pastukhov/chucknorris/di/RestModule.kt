package com.pastukhov.chucknorris.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pastukhov.chucknorris.data.ChackNorrisServiceForCoroutine
import com.pastukhov.chucknorris.data.ChackNorrisService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private val BASE_URL = "https://api.chucknorris.io/"

@Module
class RestModule {

    @Provides
    @Singleton
    fun provideChackNorris(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideChackNorrisApiService(retrofit: Retrofit): ChackNorrisServiceForCoroutine =
        retrofit.create(ChackNorrisServiceForCoroutine::class.java)

    @Provides
    @Singleton
    fun provideChackNorrisRxJavaApiService(retrofit: Retrofit): ChackNorrisService =
        retrofit.create(ChackNorrisService::class.java)
}