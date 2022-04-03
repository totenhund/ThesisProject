package com.example.thesisproject.presentation.map.mapManager.listeners

import android.location.Location
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map

class PinEventOnTapListener(private val event: (Point) -> Unit): InputListener {
    override fun onMapTap(map: Map, point: Point) {
        event.invoke(point)
    }

    override fun onMapLongTap(p0: Map, p1: Point) {}
}