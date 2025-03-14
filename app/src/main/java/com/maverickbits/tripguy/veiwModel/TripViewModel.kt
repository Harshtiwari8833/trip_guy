package com.maverickbits.tripguy.veiwModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.maverickbits.tripguy.room.dao.TripDao
import com.maverickbits.tripguy.room.entity.AmountEntry
import com.maverickbits.tripguy.room.entity.TripEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TripViewModel(private val tripDao: TripDao) : ViewModel() {


    fun addTrip(tripName: String, tripMembers: String, currentTime: String, tripId: String) {
        if (tripName.isNotBlank() && tripMembers.isNotBlank() && currentTime.isNotBlank()) {
            viewModelScope.launch {
                tripDao.insertTrip(
                    TripEntity(
                        tripName = tripName,
                        tripMembers = tripMembers,
                        currentTime = currentTime,
                        tripId = tripId
                    )
                )
            }
        }
    }

    fun addAmountDetails(
        userID: String,
        userName: String,
        category: String,
        paymentMode: String,
        location: String,
        amount: String,
        date: String,
        note: String,
        tripId: String
    ) {
        if (userID.isNotBlank() && userName.isNotBlank() && category.isNotBlank() && paymentMode.isNotBlank() && location.isNotBlank() && amount.isNotBlank() && date.isNotBlank() && note.isNotBlank() && tripId.isNotBlank()) {
            viewModelScope.launch {
                tripDao.insertAmountDetails(
                    AmountEntry(
                        userId = userID,
                        userName = userName,
                        category = category,
                        paymentMode = paymentMode,
                        location = location,
                        amount = amount,
                        date = date,
                        note = note,
                        tripId = tripId
                    )
                )
            }
        }
    }

    val allTrips: StateFlow<List<TripEntity>?> = tripDao.getAllTrips()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)


    fun deleteTripById(tripId: Int) {
        viewModelScope.launch {
            tripDao.deleteTripById(tripId)
        }
    }
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