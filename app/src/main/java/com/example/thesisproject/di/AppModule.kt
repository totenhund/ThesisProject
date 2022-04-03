package com.example.thesisproject.di

import com.example.thesisproject.data.api.EventApi
import com.example.thesisproject.domain.interactors.EventInteractor
import com.example.thesisproject.domain.repositories.EventRepository
import com.example.thesisproject.domain.repositories.impl.EventRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl(): String = "https://6249e6b1852fe6ebf881e9f1.mockapi.io/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getHttpClient())
        .build()

    @Provides
    @Singleton
    fun provideEventApi(retrofit: Retrofit): EventApi = retrofit.create(EventApi::class.java)

    @Provides
    @Singleton
    fun provideEventRepository(eventApi: EventApi): EventRepository = EventRepositoryImpl(eventApi)


    @Provides
    @Singleton
    fun provideEventInteractor(eventRepository: EventRepository): EventInteractor = EventInteractor(eventRepository)

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

}