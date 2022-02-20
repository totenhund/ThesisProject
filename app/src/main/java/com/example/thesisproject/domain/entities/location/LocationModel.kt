package com.example.thesisproject.domain.entities.location

import android.location.Location

data class LocationModel (
    val longitude: Double,
    val latitude: Double
) {
    companion object {
        fun fromLocation(location: Location): LocationModel {
            return LocationModel(location.longitude, location.latitude)
        }
    }
}