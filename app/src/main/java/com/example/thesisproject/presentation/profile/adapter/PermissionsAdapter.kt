package com.example.thesisproject.presentation.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thesisproject.R
import com.example.thesisproject.databinding.ItemPermissionBinding

class PermissionsAdapter(
    private var permissions: List<PermissionItem> = listOf()
) : RecyclerView.Adapter<PermissionsAdapter.PermissionViewHolder>() {


    inner class PermissionViewHolder(val binding: ItemPermissionBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionViewHolder =
        PermissionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_permission,
                parent,
                false
            ),
            parent.context
        )

    override fun onBindViewHolder(holder: PermissionViewHolder, position: Int) =
        holder.binding.run {
            tvPermission.text = permissions[position].title
            btnSwitch.isChecked = permissions[position].isSwitched
            btnSwitch.setOnCheckedChangeListener { _, _ ->

            }
        }

    override fun getItemCount(): Int = permissions.size
}