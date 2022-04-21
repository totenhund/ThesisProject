package com.example.thesisproject.presentation.profile

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thesisproject.R
import com.example.thesisproject.databinding.FragmentProfileBinding
import com.example.thesisproject.presentation.base.BaseFragment
import com.example.thesisproject.presentation.profile.adapter.PermissionItem
import com.example.thesisproject.presentation.profile.adapter.PermissionsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment: BaseFragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var permissionsAdapter: PermissionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        initPermissionsAdapter()
        return binding.root
    }


    private fun getGrantedPermissions(appPackage: String?): List<String> {
        val granted: MutableList<String> = ArrayList()
        try {
            val pi: PackageInfo? =
                appPackage?.let { requireContext().packageManager.getPackageInfo(it, PackageManager.GET_PERMISSIONS) }

            pi?.let {
                for (i in it.requestedPermissions.indices) {
                    if (it.requestedPermissionsFlags[i] and PackageInfo.REQUESTED_PERMISSION_GRANTED != 0) {
                        granted.add(it.requestedPermissions[i])
                    }
                }
            }

        } catch (e: Exception) {
        }
        return granted
    }

    private fun initPermissionsAdapter() {
        permissionsAdapter = PermissionsAdapter()
        binding.rvPermissions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PermissionsAdapter(getGrantedPermissions(
                "com.example.thesisproject"
            ).map {
                PermissionItem(
                    it,
                    true
                )
            })
        }
    }

}