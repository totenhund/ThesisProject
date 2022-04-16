package com.example.thesisproject.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.thesisproject.presentation.camera.CameraFragment
import com.example.thesisproject.presentation.main.view.MainFragment
import com.example.thesisproject.presentation.map.MapFragment
import com.example.thesisproject.presentation.profile.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {

    object Main : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = MainFragment()
    }

    object Camera : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = CameraFragment()
    }

    object Map : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = MapFragment()
    }

    object Profile : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = ProfileFragment()
    }

    enum class TabName {
        Map,
        Profile
    }

    data class TabScreen(private val tab: TabName) : FragmentScreen {

        override val screenKey: String
            get() = tab.name

        override fun createFragment(factory: FragmentFactory): Fragment {
            return when (tab) {
                TabName.Map -> MapFragment()
                TabName.Profile -> ProfileFragment()
            }
        }

    }

}