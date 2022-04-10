package com.example.thesisproject.presentation.camera

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.databinding.DataBindingUtil
import com.example.thesisproject.R
import com.example.thesisproject.databinding.FragmentCameraBinding
import com.example.thesisproject.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CameraFragment : BaseFragment() {

    private lateinit var binding: FragmentCameraBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askForPermissions(
            arrayOf(
                Manifest.permission.CAMERA
            ),
            onSuccess = {},
            onDeclined = {}
        )

        startCamera()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_camera,
            container,
            false
        )

        return binding.root
    }

    private fun startCamera() {
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(i, 1004)
    }

}