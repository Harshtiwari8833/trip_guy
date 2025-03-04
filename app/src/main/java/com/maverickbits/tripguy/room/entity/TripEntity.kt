package com.maverickbits.tripguy.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tripName: String,
    val tripMembers: String,
    val currentTime :String
)
