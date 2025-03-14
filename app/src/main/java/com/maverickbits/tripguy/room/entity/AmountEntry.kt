package com.maverickbits.tripguy.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "amount_details")
data class AmountEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val userName: String,
    val category: String,
    val paymentMode: String,
    val location: String,
    val amount: String,
    val date: String,
    val note : String,
    val tripId: String
)
