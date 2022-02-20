package com.example.thesisproject.presentation.map.mapManager


import com.example.thesisproject.domain.entities.location.LocationModel
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.mapview.MapView

interface MapManager {

    fun focusOnUser(location: LocationModel)

    fun addPlaceMark(location: LocationModel)

    fun addOnTapListener(listener: InputListener)

    fun onZooming(zoomValue: Int)

//    fun addEvent(event: Event)

    fun onStop()

    fun onStart()

    fun setMapView(mapView: MapView)

    fun addUserLocationLayer()

}