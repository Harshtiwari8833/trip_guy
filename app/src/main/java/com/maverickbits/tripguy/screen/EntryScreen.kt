package com.maverickbits.tripguy.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverickbits.tripguy.ui.theme.background
import java.lang.reflect.Modifier

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EntryScreen() {
    Scaffold(topBar = { Toolbar() }, floatingActionButton = {
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Default.Add, contentDescription = "Open Bottom Sheet")
        }
    })
    { paddingValues ->
        Box(
            androidx.compose.ui.Modifier
                .fillMaxSize()
                .background(background) //  Background is properly applied
                .padding(paddingValues)
                .padding(0.dp)
        ) {
            Row(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .wrapContentHeight()
                        .padding(8.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))


                )

                {
                    Row {
                        Text(
                            "Total",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black,
                            modifier = androidx.compose.ui.Modifier
                                .padding(6.dp)
                        )
                    }
                    Column(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(18.dp),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Rs 3000/-",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.W900,
                            color = Color.Black
                        )
                    }

                }
                Column(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f)
                        .padding(8.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                )
                {
                    Row {
                        Text(
                            "Individual",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black,
                            modifier = androidx.compose.ui.Modifier
                                .padding(6.dp)
                        )
                    }
                    Column(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(18.dp),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Rs 1000/-",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.W900,
                            color = Color.Black
                        )
                    }

                }

            }
        }
    }
}


