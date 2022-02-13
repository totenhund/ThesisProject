package com.example.thesisproject.presentation.map

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.thesisproject.domain.entities.location.LocationModel
import com.google.android.gms.location.LocationServices

class LocationLiveData(context: Context) : LiveData<LocationModel>() {

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override fun onInactive() {
        super.onInactive()
    }

    override fun onActive() {
        super.onActive()
    }
}