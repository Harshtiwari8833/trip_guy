package com.maverickbits.tripguy.screen

import android.annotation.SuppressLint
import android.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverickbits.tripguy.R
import com.maverickbits.tripguy.ui.theme.Purple40
import com.maverickbits.tripguy.ui.theme.background

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun TripEntry() {
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
                .background(background) // ✅ Background is properly applied
                .statusBarsPadding()
                .padding(0.dp)
        ) {
            BackgroundImage()
            Toolbar()
            body()

            // ✅ Keep Bottom Sheet inside Box so it appears over content
            if (showSheet) {
                BottomSheetContent(
                    sheetState = sheetState,
                    onDismiss = { showSheet = false }
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
fun BottomSheetContent(sheetState: SheetState, onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hello from Bottom Sheet!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            androidx.compose.material3.Button(onClick = onDismiss) {
                Text("Close Sheet")
            }
        }
    }
}


