package com.example.thesisproject.presentation.base.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class NavRouter(private val router: Router): Router() {

    fun startFlow(screen: FragmentScreen) {
        router.navigateTo(screen)
    }

    fun newRootFlow(screen: FragmentScreen) {
        router.newRootScreen(screen)
    }

    fun finishFlow() {
        router.exit()
    }

    fun navigateInCurrentFlow(vararg screens: FragmentScreen) {
        newChain(*screens)
    }
}