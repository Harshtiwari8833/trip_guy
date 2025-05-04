package com.maverickbits.tripguy.screen

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverickbits.tripguy.R
import com.maverickbits.tripguy.ui.theme.background
import com.maverickbits.tripguy.ui.theme.redBackGround
import com.maverickbits.tripguy.ui.theme.redText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


    @Composable
    @Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
    fun AmoutEntry() {
        var textTitleState by remember {
            mutableStateOf("0.00")
        }
        var noteText by remember {
            mutableStateOf("")
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        var selectedDate by remember { mutableStateOf(dateFormat.format(Date())) } // Default: Today
        var expanded by remember { mutableStateOf(false) }
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
                        text = "Edit",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Red,
                        modifier = Modifier.clickable {



                            if (noteText.isNotBlank() && textTitleState.isNotBlank() && !textTitleState.equals("0.00")) {

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
                        Text("0.00", fontSize = 24.sp, color = Color.Red, fontWeight = FontWeight.Bold)
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
                Text(

                    "Notes",
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
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
                        .padding(start = 5.dp)
                        .size(25.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text(

                        "25/04/2025",
                        fontSize = 18.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
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
                        text = "Cash", // Show selected payment method
                        fontSize = 18.sp,
                        color = Color.DarkGray
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
                    painter = painterResource(R.drawable.location), contentDescription = "pen",
                    tint = redText,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(25.dp)
                )
                Text(
                    "Gurgaon",
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
