package com.maverickbits.tripguy.routes

import kotlinx.serialization.Serializable

sealed interface Screens{
    @Serializable
    data object LoginScreen : Screens
    @Serializable
    data object FillDetailsScreen : Screens
    @Serializable
    data object TripEntryScreen : Screens
    @Serializable
    data class AmountEntryScreen(
        val tripId : String
    ) : Screens
    @Serializable
    data class EntryScreen (
        val tripId : String
    ): Screens
}