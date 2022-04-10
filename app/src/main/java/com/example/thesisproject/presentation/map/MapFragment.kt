package com.example.thesisproject.presentation.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.thesisproject.BuildConfig
import com.example.thesisproject.R
import com.example.thesisproject.databinding.FragmentMapBinding
import com.example.thesisproject.data.entities.LocationModel
import com.example.thesisproject.domain.network.Resource
import com.example.thesisproject.presentation.base.BaseFragment
import com.example.thesisproject.presentation.map.mapManager.YandexMapManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : BaseFragment() {


    companion object {
        const val GPS_REQUEST = 101
    }


    private lateinit var binding: FragmentMapBinding

    private lateinit var mapManager: YandexMapManager

    private val mapViewModel by viewModels<MapViewModel>()


    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var isGPSEnabled = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mapManager = YandexMapManager(requireContext())

        GpsUtils(requireContext(), requireActivity()).turnGPSOn(object : GpsUtils.OnGpsListener {

            override fun gpsStatus(isGPSEnable: Boolean) {
                isGPSEnabled = isGPSEnable
            }
        })

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_map,
            container,
            false
        )

        mapManager.setMapView(binding.mapview)

        setControls()

        setFocusOnUser()

        initEvents()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        askForPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            onSuccess = {
                mapManager.addUserLocationLayer()
                setFocusOnUser()
            },
            onDeclined = {
                showPermissionIsNeeded()
            }
        )
    }


    private fun initEvents() {
        mapViewModel.getEvents().observe(viewLifecycleOwner, { res ->
            when(res.status) {
                Resource.Status.SUCCESS -> {
                    res.data?.forEach { event ->
                        mapManager.addPlaceMark(event)
                    }
                }
                else -> {

                }
            }
        })
    }


    private fun setControls() {
        binding.btnFocusLocation.setOnClickListener {
            setFocusOnUser()
        }
    }


    private fun setFocusOnUser() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    mapManager.focusOnUser(LocationModel.fromLocation(it))
                }
            }
    }




    override fun onStop() {
        mapManager.onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        mapManager.onStart()
    }


    private fun showPermissionIsNeeded() {
        Snackbar.make(
            binding.root,
            R.string.permission_denied_explanation,
            Snackbar.LENGTH_LONG
        )
            .setAction(R.string.settings) {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts(
                    "package",
                    BuildConfig.APPLICATION_ID,
                    null
                )
                intent.data = uri
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            .show()
    }

}