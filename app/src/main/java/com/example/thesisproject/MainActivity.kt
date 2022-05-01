package com.example.thesisproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.thesisproject.databinding.ActivityMainBinding
import com.example.thesisproject.domain.interactors.AuthInteractor
import com.example.thesisproject.domain.interactors.RealtimeDatabaseInteractor
import com.example.thesisproject.presentation.Screens
import com.example.thesisproject.presentation.base.BaseFragment
import com.example.thesisproject.presentation.base.navigation.NavRouter
import com.example.thesisproject.shortcuts.showLongSnackbar
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var router: NavRouter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var authInteractor: AuthInteractor

    @Inject
    lateinit var realtimeDatabaseInteractor: RealtimeDatabaseInteractor

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    private val navigator = AppNavigator(this, R.id.container)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        realtimeDatabaseInteractor.getEvents()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val done = currentFragment?.onBackPressed()
        if (done != false)
            super.onBackPressed()
    }


    override fun onStart() {
        super.onStart()

        // Check if user is authenticated
        if (authInteractor.isAuthenticated()) {
            router.newRootFlow(Screens.Main)
        } else {
            startAuth(authInteractor.buildSignIntent())
        }

    }

    private fun startAuth(intent: Intent) =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                val response = IdpResponse.fromResultIntent(it.data)
                if (response == null) {
                    finish()
                }

                if (response?.error?.errorCode == ErrorCodes.NO_NETWORK) {
                    binding.root.showLongSnackbar(R.string.no_connection)
                }
            }
        }.launch(intent)

}