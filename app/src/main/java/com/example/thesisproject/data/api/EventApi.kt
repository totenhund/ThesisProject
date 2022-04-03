package com.example.thesisproject.data.api

import com.example.thesisproject.data.entities.Event
import retrofit2.Response
import retrofit2.http.GET

interface EventApi {

    @GET("events")
    suspend fun getEvents(): Response<List<Event>>

}