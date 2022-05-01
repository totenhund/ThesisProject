package com.example.thesisproject.domain.interactors

import com.example.thesisproject.data.entities.Event
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class RealtimeDatabaseInteractor @Inject constructor(private val database: FirebaseDatabase) {

    fun getEvents(): List<Event>? {

        var events: List<Event>? = null

        database.getReference("events")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    events = snapshot.value as? List<Event>
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        return events

    }



}