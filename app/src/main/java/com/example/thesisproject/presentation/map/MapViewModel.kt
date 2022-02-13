package com.example.thesisproject.presentation.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MapViewModel(application: Application): AndroidViewModel(application) {

    val locationData = LocationLiveData(application)

}