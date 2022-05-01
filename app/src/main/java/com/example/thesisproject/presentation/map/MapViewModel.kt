package com.example.thesisproject.presentation.map

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesisproject.domain.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.liveData
import com.example.thesisproject.data.entities.Event
import com.example.thesisproject.domain.interactors.EventInteractor
import com.example.thesisproject.domain.interactors.RealtimeDatabaseInteractor
import com.example.thesisproject.domain.network.Resource
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    application: Application,
    private val realtimeDatabaseInteractor: RealtimeDatabaseInteractor
) : AndroidViewModel(application) {

    val locationData = LocationLiveData(application)


    fun getEvents() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = realtimeDatabaseInteractor.getEvents()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Empty"))
        }
    }

}