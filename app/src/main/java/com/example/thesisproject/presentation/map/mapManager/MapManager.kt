package com.example.thesisproject.presentation.map.mapManager


import com.example.thesisproject.data.entities.Event
import com.example.thesisproject.data.entities.LocationModel
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.mapview.MapView

interface MapManager {

    fun focusOnUser(location: LocationModel)

    fun addPlaceMark(event: Event)

    fun addOnTapListener(listener: InputListener)

    fun onZooming(zoomValue: Int)

//    fun addEvent(event: Event)

    fun onStop()

    fun onStart()

    fun setMapView(mapView: MapView)

    fun addUserLocationLayer()

}