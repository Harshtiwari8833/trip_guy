package com.maverickbits.tripguy.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maverickbits.tripguy.R
import com.maverickbits.tripguy.routes.Routes
import com.maverickbits.tripguy.ui.theme.background
import java.lang.reflect.Modifier


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EntryScreen(tripId: String, navController: NavController) {
    Scaffold(topBar = { Toolbar() }, floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate("${Routes.AmountEntryScreen}/$tripId") }) {
            Icon(Icons.Default.Add, contentDescription = Routes.AmountEntryScreen)
        }
    }) { paddingValues ->
        Column (
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
                ) {
                    Row {
                        Text(
                            "Total",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black,
                            modifier = androidx.compose.ui.Modifier.padding(6.dp)
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
                ) {
                    Row {
                        Text(
                            "Individual",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black,
                            modifier = androidx.compose.ui.Modifier.padding(6.dp)
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

            Text("Entries")
            LazyColumn {
                items() {
                    ListItem()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListItem() {
    Row(modifier = androidx.compose.ui.Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.sleeping),
                contentDescription = "category",
                androidx.compose.ui.Modifier
                    .padding(20.dp)
                    .size(40.dp),
            )
            Column{
                Text(text = "Room", textAlign = TextAlign.Center, fontSize = 16.sp, fontWeight = FontWeight.W900)
                Text(text = "Accommodation")
            }
        }
        Text(text = "30000/-" ,  androidx.compose.ui.Modifier.padding(20.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEntryScreen() {
    // Create a dummy NavController
    val navController = rememberNavController()

    EntryScreen(tripId = "123", navController = navController)
}

