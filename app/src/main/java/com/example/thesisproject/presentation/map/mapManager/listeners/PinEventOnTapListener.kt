package com.example.thesisproject.presentation.map.mapManager.listeners

import android.location.Location
import android.util.Log
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map

class PinEventOnTapListener(private val event: (Point) -> Unit): InputListener {
    override fun onMapTap(map: Map, point: Point) {}

    override fun onMapLongTap(map: Map, point: Point) {
        event.invoke(point)
    }
}