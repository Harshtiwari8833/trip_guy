package com.maverickbits.tripguy.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maverickbits.tripguy.room.entity.AmountEntry
import com.maverickbits.tripguy.room.entity.TripEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: TripEntity)

    @Query("SELECT * FROM trips")
    fun getAllTrips(): Flow<List<TripEntity>>

    @Query("DELETE FROM trips WHERE id = :tripId")
    suspend fun deleteTripById(tripId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAmountDetails(trip: AmountEntry)

    @Query("SELECT * FROM amount_details")
   fun fetchAllAmountDetails(): Flow<List<AmountEntry>>
}