package com.example.thesisproject.presentation.main.viewModel

import androidx.lifecycle.ViewModel
import com.example.thesisproject.presentation.Screens
import com.example.thesisproject.presentation.base.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val navRouter: NavRouter) : ViewModel() {


    fun onCameraPressed() {
        navRouter.startFlow(Screens.Camera)
    }

    fun finish() {
        navRouter.finishFlow()
    }

}