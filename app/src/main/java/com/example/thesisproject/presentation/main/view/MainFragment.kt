package com.example.thesisproject.presentation.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import com.example.thesisproject.R
import com.example.thesisproject.databinding.FragmentMainBinding
import com.example.thesisproject.presentation.Screens
import com.example.thesisproject.presentation.base.BaseFragment
import com.example.thesisproject.presentation.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel by viewModels<MainViewModel>()

    private var currentTab = Screens.TabName.Map

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTab(Screens.TabName.Map)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setUpControls()
        setBottomNavigation()
        return binding.root
    }

    override fun onBackPressed(): Boolean {

        val currentFragment = childFragmentManager.fragments.firstOrNull {f -> f.isVisible} as? BaseFragment

        if (currentFragment?.onBackPressed() != false) onBackFromTabClicked()

        return false
    }

    private fun setUpControls() {
        binding.fabCamera.setOnClickListener {
            viewModel.onCameraPressed()
        }
    }

    private fun setTab(tab: Screens.TabName) {
        val fm = childFragmentManager

        fm.beginTransaction().apply {
            fm.fragments.forEach {
                hide(it)
            }

            val selectedFragment = fm.findFragmentByTag(tab.name)

            if (selectedFragment == null) {
                add(
                    R.id.main_container,
                    Screens.TabScreen(tab).createFragment(FragmentFactory()),
                    tab.name
                )
            } else {
                show(selectedFragment)
            }
        }.commitNow()
    }

    private fun onTabClicked(tab: Screens.TabName) {
        currentTab = tab
        setTab(tab)
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.map -> {
                    onTabClicked(Screens.TabName.Map)
                    true
                }
                R.id.profile -> {
                    onTabClicked(Screens.TabName.Profile)
                    true
                }
                else -> false
            }

        }
    }


    fun onBackFromTabClicked() {
        if (currentTab == Screens.TabName.Profile) {
            onTabClicked(Screens.TabName.Map)
            bottom_navigation.selectedItemId = R.id.map
        } else {
            viewModel.finish()
        }
    }


}