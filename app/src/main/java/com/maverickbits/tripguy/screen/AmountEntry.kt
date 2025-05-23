package com.maverickbits.tripguy.screen

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverickbits.tripguy.R
import com.maverickbits.tripguy.ui.theme.background
import com.maverickbits.tripguy.ui.theme.redBackGround
import com.maverickbits.tripguy.ui.theme.redText
import android.app.DatePickerDialog
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.maverickbits.tripguy.veiwModel.TripViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AmoutEntry(tripId: String, viewModel: TripViewModel, navController: NavController) {
    var textTitleState by remember {
        mutableStateOf("0.00")
    }
    var noteText by remember {
        mutableStateOf("")
    }

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormat.format(Date())) } // Default: Today
    val options = listOf("Cash", "Card", "UPI")
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                selectedDate = "$dayOfMonth/${month + 1}/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    var selectedLocation by remember { mutableStateOf("Fetching...") }
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

// Fetch the user's location
    LaunchedEffect(Unit) {
        getUserLocation(context, fusedLocationClient) { locationName ->
            selectedLocation = locationName // Update state
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(background)
            .statusBarsPadding()
    ) {

        Column(Modifier.background(redBackGround)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .scale(1f)
                            .size(20.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        tint = redText
                    )
                    Text(
                        text = "Entries",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Color.Red
                    )
                }
                Text(
                    text = "Save",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Red,
                    modifier = Modifier.clickable {

                        val userName =
                            navController.context.getSharedPreferences(
                                "userData",
                                Context.MODE_PRIVATE
                            )
                                .getString("userImg", "")

                        val userEmail =
                            navController.context.getSharedPreferences(
                                "userData",
                                Context.MODE_PRIVATE
                            )
                                .getString("userEmail", "")

                        viewModel.addAmountDetails(
                            tripId = tripId,
                            userID = userEmail!!,
                            userName = userName!!,
                            category = "Hotel",
                            paymentMode = selectedOption,
                            location = selectedLocation,
                            amount = textTitleState,
                            date = selectedDate,
                            note = noteText
                        )

                        if (noteText.isNotBlank() && textTitleState.isNotBlank() && !textTitleState.equals("0.00")) {
                            navController.popBackStack()
                        }else{
                            Toast.makeText(
                                navController.context,
                                "Please enter all the fields",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.sleeping),
                    contentDescription = "category",
                    Modifier.size(40.dp),
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(value = textTitleState,
                        onValueChange = { textTitleState = it },
                        Modifier.wrapContentSize(),
                        textStyle = TextStyle(
                            Color.Red, 24.sp,
                            FontWeight.Bold,
                            textAlign = TextAlign.Right
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        decorationBox = {
                            Box {
                                if (textTitleState.isEmpty())
                                    Text("", fontSize = 24.sp, color = Color.Red)
                                it()
                            }
                        })
                    Image(
                        painter = painterResource(R.drawable.rupees),
                        contentDescription = "category",
                        Modifier.size(55.dp)
                    )
                }
            }
        }


        Spacer(Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.pen), contentDescription = "pen",
                tint = redText,
                modifier = Modifier
                    .padding(5.dp)
                    .size(25.dp)
            )
            BasicTextField(value = noteText,
                onValueChange = { noteText = it },
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                textStyle = TextStyle(
                    fontSize = 18.sp, color = Color.DarkGray
                ),
                decorationBox = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (noteText.isEmpty())
                            Text(
                                "Notes",
                                fontSize = 18.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.fillMaxWidth()
                            )
                        it()
                    }
                }
            )
        }
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.calendar), contentDescription = "pen",
                tint = redText,
                modifier = Modifier
                    .padding(5.dp)
                    .size(25.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                BasicTextField(
                    value = selectedDate,
                    onValueChange = {}, // No need to allow user typing
                    textStyle = TextStyle(fontSize = 18.sp),
                    readOnly = true, // Make it read-only
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            DatePickerDialog(
                                context,
                                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                                    selectedDate = "$dayOfMonth/${month + 1}/$year"
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                            ).show()
                        }

                )
            }
        }

        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.wallet), contentDescription = "pen",
                tint = redText,
                modifier = Modifier
                    .padding(5.dp)
                    .size(25.dp)
            )
            // Clickable Box to show selected payment method
            Box(
                modifier = Modifier
                    .weight(1f) // Takes available width
                    .padding(horizontal = 8.dp)
                    .clickable { expanded = true }

            ) {
                Text(
                    text = selectedOption, // Show selected payment method
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            )
            {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option // Update selected value
                            expanded = false // Close menu
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.location), contentDescription = "pen",
                tint = redText,
                modifier = Modifier
                    .padding(5.dp)
                    .size(25.dp)
            )
            Text(
                selectedLocation,
                fontSize = 18.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@SuppressLint("MissingPermission") // Make sure to handle permissions in UI
fun getUserLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationFetched: (String) -> Unit
) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        if (location != null) {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses: List<Address>? = geocoder.getFromLocation(location.latitude, location.longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                val cityName = addresses[0].locality ?: "Unknown Location"
                onLocationFetched(cityName) // Update location in UI
            } else {
                onLocationFetched("Location Not Found")
            }
        } else {
            onLocationFetched("Unable to get location")
        }
    }
}