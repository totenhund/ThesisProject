package com.example.thesisproject.presentation.map.mapManager


import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.thesisproject.R
import com.example.thesisproject.data.entities.Event
import com.example.thesisproject.data.entities.LocationModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.*
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider


class YandexMapManager(private val context: Context) : MapManager {


    companion object {
        private const val ZOOM_VALUE = 17f
        private const val AZIMUTH = 0f
        private const val TILT = 0f
        private const val ANIM_DURATION = 0.5f
    }

    private var searchManager: SearchManager
    private var locationManager: LocationManager
    private var mapView: MapView? = null
    private var isFocusing: Boolean = true
    private var cameraListener: CameraListener = CameraListener { _, _, cameraUpdateReason, _ ->
        if (cameraUpdateReason == CameraUpdateReason.GESTURES) {
            isFocusing = false
        }
    }

    private var locationListener: LocationListener = object : LocationListener {
        override fun onLocationUpdated(location: Location) {

        }

        override fun onLocationStatusUpdated(locationStatus: LocationStatus) {

        }

    }


    private var userLayer: UserLocationLayer? = null


    private var objectListener = object : UserLocationObjectListener {
        override fun onObjectAdded(userLocationView: UserLocationView) {
            userLayer?.setAnchor(
                PointF((mapView!!.width * 0.5).toFloat(), (mapView!!.height * 0.5).toFloat()),
                PointF((mapView!!.width * 0.5).toFloat(), (mapView!!.height * 0.83).toFloat())
            )


            userLocationView.arrow.setIcon(
                ImageProvider.fromResource(
                    context, R.drawable.user_arrow
                )
            )

            val pinIcon = userLocationView.pin.useCompositeIcon()

//            pinIcon.setIcon(
//                "icon",
//                ImageProvider.fromResource(context, R.drawable.icon),
//                IconStyle().setAnchor(PointF(0f, 0f))
//                    .setRotationType(RotationType.ROTATE)
//                    .setZIndex(0f)
//                    .setScale(1f)
//            )

            pinIcon.setIcon(
                "pin",
                ImageProvider.fromResource(context, R.drawable.search_result),
                IconStyle().setAnchor(PointF(0.5f, 0.5f))
                    .setRotationType(RotationType.ROTATE)
                    .setZIndex(1f)
                    .setScale(0.5f)
            )

            userLocationView.accuracyCircle.fillColor = Color.BLUE and -0x66000001
        }

        override fun onObjectRemoved(p0: UserLocationView) {

        }

        override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {

        }

    }


    init {
        MapKitFactory.initialize(context)
        SearchFactory.initialize(context)
        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)
        locationManager = MapKitFactory.getInstance().createLocationManager()
    }

    override fun focusOnUser(location: LocationModel) {
        mapView?.map?.move(
            CameraPosition(
                Point(location.latitude, location.longitude), ZOOM_VALUE, AZIMUTH, TILT
            ),
            Animation(Animation.Type.SMOOTH, ANIM_DURATION),
            null
        )
        isFocusing = true
    }


    override fun addPlaceMark(event: Event) {
        mapView?.map?.mapObjects?.addPlacemark(
            Point(
                event.location.longitude,
                event.location.latitude
            ),
            ViewProvider(View(context).apply {
                background = when (event.emergency) {
                    Event.Emergency.LIGHT -> {
                        AppCompatResources.getDrawable(context, R.drawable.light_emergency)
                    }
                    Event.Emergency.MEDIUM -> {
                        AppCompatResources.getDrawable(context, R.drawable.medium_emergency)
                    }
                    Event.Emergency.HIGH -> {
                        AppCompatResources.getDrawable(context, R.drawable.high_emergency)
                    }
                }
            })
        )?.addTapListener { obj, point ->
            Toast.makeText(context, event.title, Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun addOnTapListener(listener: InputListener) {
        mapView?.map?.addInputListener(listener)
    }

    override fun onZooming(zoomValue: Int) {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        mapView?.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        mapView?.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun setMapView(mapView: MapView) {
        this.mapView = mapView
//        addUserLocationLayer()
//        locationManager.subscribeForLocationUpdates(0.0, 0, 0.0, true, FilteringMode.ON, locationListener)
    }


    override fun addUserLocationLayer() {
        mapView?.let {
            userLayer = MapKitFactory.getInstance().createUserLocationLayer(it.mapWindow)
            userLayer?.isVisible = true
            userLayer?.isHeadingEnabled = true
            userLayer?.setObjectListener(objectListener)
        }
    }

}