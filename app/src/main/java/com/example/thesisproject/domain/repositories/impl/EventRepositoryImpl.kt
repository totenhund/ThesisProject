package com.example.thesisproject.domain.repositories.impl

import com.example.thesisproject.data.api.EventApi
import com.example.thesisproject.data.entities.Event
import com.example.thesisproject.domain.repositories.EventRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepositoryImpl @Inject constructor (private val eventApi: EventApi): EventRepository {
    override suspend fun getAllEvent(): Response<List<Event>> = eventApi.getEvents()
}