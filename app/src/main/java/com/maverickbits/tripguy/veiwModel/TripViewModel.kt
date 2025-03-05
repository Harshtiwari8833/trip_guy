package com.maverickbits.tripguy.veiwModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.maverickbits.tripguy.room.dao.TripDao
import com.maverickbits.tripguy.room.entity.TripEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TripViewModel(private val tripDao: TripDao) : ViewModel() {



    fun addTrip(tripName: String, tripMembers: String, currentTime: String) {
        if (tripName.isNotBlank() && tripMembers.isNotBlank() && currentTime.isNotBlank()) {
            viewModelScope.launch {
                tripDao.insertTrip(TripEntity(tripName = tripName, tripMembers = tripMembers, currentTime = currentTime))
            }
        }
    }

    val allTrips: StateFlow<List<TripEntity>> = tripDao.getAllTrips()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}

class TripViewModelFactory(private val tripDao: TripDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TripViewModel(tripDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}