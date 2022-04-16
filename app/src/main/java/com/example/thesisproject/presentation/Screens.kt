package com.example.thesisproject.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.thesisproject.presentation.camera.CameraFragment
import com.example.thesisproject.presentation.map.MapFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {

    object Camera : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = CameraFragment()
    }

    object Map : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = MapFragment()
    }

}