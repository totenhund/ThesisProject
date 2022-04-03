package com.example.thesisproject.domain.repositories

import com.example.thesisproject.data.entities.Event
import retrofit2.Response

interface EventRepository {

    suspend fun getAllEvent(): Response<List<Event>>

}