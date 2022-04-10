package com.example.thesisproject.domain.interactors

import com.example.thesisproject.data.entities.Event
import com.example.thesisproject.domain.repositories.EventRepository
import javax.inject.Inject

class EventInteractor @Inject constructor(private val eventRepository: EventRepository) {

    suspend fun getEvents(): List<Event>? = eventRepository.getAllEvent().body()

    suspend fun addEvent(event: Event) = eventRepository.addEvent(event)

}