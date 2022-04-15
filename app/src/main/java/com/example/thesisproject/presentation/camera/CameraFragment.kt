package com.example.thesisproject.presentation.camera

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.thesisproject.R
import com.example.thesisproject.databinding.FragmentCameraBinding
import com.example.thesisproject.presentation.base.BaseFragment
import com.example.thesisproject.presentation.ui.camera.CameraManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@AndroidEntryPoint
class CameraFragment : BaseFragment() {


    companion object {
        private const val TAG = "CAMERA_FRAGMENT"
    }

    private val imageCapture = CameraManager.buildImageCapture()

    private lateinit var binding: FragmentCameraBinding

    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraExecutor = Executors.newSingleThreadExecutor()

        askForPermissions(
            arrayOf(
                Manifest.permission.CAMERA
            ),
            onSuccess = {
                startCamera()
            },
            onDeclined = {}
        )


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

        setUpControls()

        return binding.root
    }


    private fun startCamera() {
        val cameraProviderFuture = CameraManager
            .cameraProvider(requireContext())

        cameraProviderFuture.addListener({

            val cameraProvider = cameraProviderFuture.get()

            val cameraPreview = CameraManager.buildPreview()

            cameraPreview.setSurfaceProvider(
                binding.viewFinder.surfaceProvider
            )

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    cameraPreview, imageCapture)

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }

        }, ContextCompat.getMainExecutor(requireContext()))

    }

    private fun takePhoto() {
        imageCapture.takePicture(
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    Log.e(TAG, image.toString())

                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e(TAG, exception.toString())
                }
            }
        )
    }

    private fun setUpControls() {
        binding.fabTakePhoto.setOnClickListener {
            takePhoto()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}