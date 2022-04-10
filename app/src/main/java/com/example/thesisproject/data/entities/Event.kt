package com.example.thesisproject.data.entities

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Event (
    @SerializedName("id") val id: Int,
    @SerializedName("date") val date: String,
    @SerializedName("emergency") val emergency: Emergency,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("location") val location: LocationModel,
    @SerializedName("photo") val photo: MultipartBody.Part?,
    @SerializedName("video") val video: MultipartBody.Part?
) {
    enum class Emergency {
        @SerializedName("LIGHT") LIGHT,
        @SerializedName("MEDIUM") MEDIUM,
        @SerializedName("HIGH") HIGH
    }
}