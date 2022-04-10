package com.example.thesisproject.data.api

import com.example.thesisproject.data.entities.Event
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {

    @GET("events")
    suspend fun getEvents(): Response<List<Event>>

    @POST("events/add")
    suspend fun addEvent(@Body event: Event): Response<Void>

}