package com.example.thesisproject.presentation.base

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    fun askForPermissions(permissions: Array<String>, onSuccess: () -> Unit, onDeclined: () -> Unit) =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { areGranted ->
            var areAllGranted = true
            areGranted.values.forEach { isGranted ->
                areAllGranted = isGranted && areAllGranted
            }

            if (areAllGranted) {
                onSuccess.invoke()
            } else {
                onDeclined.invoke()
            }

        }.launch(permissions)

    fun askForPermission(permission: String, onSuccess: () -> Unit, onDeclined: () -> Unit) = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            onSuccess.invoke()
        } else {
            onDeclined.invoke()
        }
    }.launch(permission)

    open fun onBackPressed(): Boolean = true

}