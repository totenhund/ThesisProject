package com.example.thesisproject.presentation.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.thesisproject.presentation.map.mapManager.YandexMapManager

class MapViewModel(application: Application): AndroidViewModel(application) {

    val locationData = LocationLiveData(application)

}