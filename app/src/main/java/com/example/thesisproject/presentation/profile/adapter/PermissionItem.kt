package com.example.thesisproject.presentation.profile.adapter

data class PermissionItem(
    val title: String,
    val isSwitched: Boolean
) {

    enum class Permission {
        Internet,
        Camera,
        FineLocation,
        CoarseLocation
    }

}