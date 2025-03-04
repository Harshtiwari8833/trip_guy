package com.maverickbits.tripguy.screen

import android.annotation.SuppressLint
import android.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverickbits.tripguy.R
import com.maverickbits.tripguy.ui.theme.Purple40
import com.maverickbits.tripguy.ui.theme.background
import com.maverickbits.tripguy.veiwModel.TripViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripEntry(viewModel: TripViewModel) {
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "Open Bottom Sheet")
            }
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(background) //  Background is properly applied
                .statusBarsPadding()
                .padding(0.dp)
        ) {
            BackgroundImage()
            Toolbar()
            body()

            //  Keep Bottom Sheet inside Box so it appears over content
            if (showSheet) {
                BottomSheetContent(
                    sheetState = sheetState,
                    onDismiss = { showSheet = false },
                    onSave = { tripName, tripMembers, currentTime ->
                        viewModel.addTrip(tripName, tripMembers, currentTime)
                        showSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun Toolbar(){
    Text("Trip Guy", fontSize = 24.sp, fontWeight = FontWeight.W700,
        modifier = Modifier.padding(10.dp))
}

@Composable
fun body() {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("It's pretty empty here...")
        Text(
            "Enter Your First Trip",
            fontSize = 28.sp,
            fontWeight = FontWeight.W900,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun BackgroundImage() {
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.haze),
            contentDescription = "haze",
            modifier = Modifier.padding(vertical = 50.dp)
        )
        Image(
            painter = painterResource(R.drawable.backpacker),
            contentDescription = "arrived",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(300.dp)
        )


    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(sheetState: SheetState, onDismiss: () -> Unit , onSave: (String, String, String) -> Unit) {

    LaunchedEffect(Unit) {
        sheetState.expand() // 🔥 Expands the bottom sheet to full-screen when opened
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.fillMaxSize()

    ) {
        BottomSheetLayout(onSave = onSave)

    }
}


@Composable
fun BottomSheetLayout(onSave: (String, String, String) -> Unit){
    var textTitleState by remember {
        mutableStateOf("")
    }
    var textTitleState1 by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Cancel",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = "Add",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier =  Modifier.clickable {
                    val currentTime = getCurrentDateTime()
                    if (textTitleState.isNotEmpty() && textTitleState1.isNotEmpty() && currentTime.isNotEmpty()){
                        onSave(textTitleState, textTitleState1,currentTime )
                }
                }
            )
        }
        Text(
            text = "Add Trip",
            fontSize = 23.sp,
            fontWeight = FontWeight.W900,
            textAlign = TextAlign.Center ,// Ensures text itself is centered
            modifier = Modifier.padding(vertical = 12.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)) // TextField background color
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
        ) {

            BasicTextField(value = textTitleState,
                onValueChange = { textTitleState = it },
                Modifier.wrapContentSize(),
                textStyle = TextStyle(
                    Color.Black, 16.sp,
                    FontWeight.Bold
                ),
                decorationBox = {
                    Box {
                        if (textTitleState.isEmpty())
                            Text("Enter Trip Name", fontSize = 16.sp, color = Color.Gray)
                        it()
                    }
                })
        }

        Row( Modifier
            .fillMaxWidth()
            .wrapContentHeight().padding(vertical = 5.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)) // TextField background color
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
            ) {

                BasicTextField(value = textTitleState1,
                    onValueChange = { textTitleState1 = it },
                    Modifier.wrapContentSize(),
                    textStyle = TextStyle(
                        Color.Black, 16.sp,
                        FontWeight.Bold
                    ),
                    decorationBox = {
                        Box {
                            if (textTitleState1.isEmpty())
                                Text("Enter Members", fontSize = 16.sp, color = Color.Gray)
                            it()
                        }
                    })
            }


        }

    }
}


@Composable
fun ListItems(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(vertical = 13.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,

    ){
        Text("Gujarat",  fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(4.dp))
        Text("3 Members | Yesterday at 5:55 PM",  fontSize = 14.sp,
            fontWeight = FontWeight.W300,
            modifier = Modifier.padding(4.dp))
    }
}

fun getCurrentDateTime(): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()) // Example: 04-03-2025 10:30 AM
    return sdf.format(Date())
}
