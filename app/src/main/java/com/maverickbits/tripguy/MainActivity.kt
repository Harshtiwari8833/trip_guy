package com.maverickbits.tripguy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.tripguy.room.database.TripDatabase

import com.maverickbits.tripguy.screen.LoginScreen
import com.maverickbits.tripguy.screen.TripEntry
import com.maverickbits.tripguy.screen.TripListScreen
import com.maverickbits.tripguy.ui.theme.TripGuyTheme
import com.maverickbits.tripguy.veiwModel.TripViewModel
import com.maverickbits.tripguy.veiwModel.TripViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Get TripDao instance (assuming you have a database instance)
        val tripDao = TripDatabase.getDatabase(this).tripDao()
        // Create ViewModel using Factory
        val viewModel: TripViewModel = ViewModelProvider(
            this, TripViewModelFactory(tripDao)
        )[TripViewModel::class.java]
        setContent {
            TripGuyTheme {
                TripEntry(viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.statusBarsPadding()
    )
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun GreetingPreview() {
    TripGuyTheme {
        Greeting("Android")
    }
}