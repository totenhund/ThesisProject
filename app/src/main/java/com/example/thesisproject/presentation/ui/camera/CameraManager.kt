package com.example.thesisproject.presentation.ui.camera

import android.content.Context
import android.view.Surface.ROTATION_0
import androidx.camera.core.AspectRatio
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY
import androidx.camera.core.ImageCapture.FLASH_MODE_AUTO
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider

object CameraManager {

    private const val aspectRatio = AspectRatio.RATIO_4_3
    private const val rotation = ROTATION_0
    private const val flashMode = FLASH_MODE_AUTO
    private const val captureMode = CAPTURE_MODE_MINIMIZE_LATENCY

    fun buildPreview(): Preview {
        return Preview.Builder()
            .setTargetAspectRatio(aspectRatio)
            .setTargetRotation(rotation)
            .build()
    }

    fun buildImageCapture(): ImageCapture {
        return ImageCapture.Builder()
            .setTargetAspectRatio(aspectRatio)
            .setTargetRotation(rotation)
            .setFlashMode(flashMode)
            .setCaptureMode(captureMode)
            .build()
    }

    fun cameraProvider(context: Context) =
        ProcessCameraProvider.getInstance(context)

}