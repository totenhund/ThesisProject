package com.example.thesisproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.thesisproject.databinding.ActivityMainBinding
import com.example.thesisproject.presentation.Screens
import com.example.thesisproject.presentation.base.navigation.NavRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var router: NavRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        router.newRootFlow(Screens.Map)
        setUpControls()
    }

    private fun setUpControls() {
        binding.fabCamera.setOnClickListener {
            router.newRootFlow(Screens.Camera)
        }
    }

}