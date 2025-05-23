package com.maverickbits.tripguy.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maverickbits.tripguy.room.dao.TripDao
import com.maverickbits.tripguy.room.entity.AmountEntry
import com.maverickbits.tripguy.room.entity.TripEntity

@Database(entities = [TripEntity::class, AmountEntry::class], version = 1, exportSchema = false)
abstract class TripDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao

    companion object {
        @Volatile
        private var INSTANCE: TripDatabase? = null

        fun getDatabase(context: Context): TripDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TripDatabase::class.java,
                    "trip_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
