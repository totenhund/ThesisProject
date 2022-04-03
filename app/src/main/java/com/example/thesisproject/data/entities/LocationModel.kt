package com.example.thesisproject.data.entities

import android.location.Location
import com.google.gson.annotations.SerializedName

data class LocationModel (
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("latitude") val latitude: Double
) {
    companion object {
        fun fromLocation(location: Location): LocationModel {
            return LocationModel(location.longitude, location.latitude)
        }
    }
}