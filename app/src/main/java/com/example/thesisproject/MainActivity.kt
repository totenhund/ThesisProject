package com.example.thesisproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.thesisproject.databinding.ActivityMainBinding
import com.example.thesisproject.presentation.Screens
import com.example.thesisproject.presentation.base.BaseFragment
import com.example.thesisproject.presentation.base.navigation.NavRouter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var router: NavRouter

    @Inject lateinit var navigatorHolder: NavigatorHolder

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    private val navigator = AppNavigator(this, R.id.container)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        router.newRootFlow(Screens.Main)
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

}