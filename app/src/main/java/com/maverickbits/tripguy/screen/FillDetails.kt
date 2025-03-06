package com.maverickbits.tripguy.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.maverickbits.tripguy.R
import com.maverickbits.tripguy.routes.Routes
import com.maverickbits.tripguy.ui.theme.background


@Composable
fun FillDetails(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(background)
            .statusBarsPadding()
    ) {
        Toolbar(text = "Cancel")
        MainContent()
        Spacer(modifier = Modifier.weight(1f,true))
        Button("Continue", {navController.navigate(Routes.TripEntryScreen)})
    }
}

@Composable
fun Toolbar(modifier: Modifier = Modifier, text: String) {
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = modifier
                    .scale(1f)
                    .size(20.dp)
                ,
                tint = Color.Black
            )
            Text(
                text = "Back",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun MainContent() {
    var textTitleState by remember {
        mutableStateOf("")
    }

    Spacer(modifier = Modifier.height(80.dp))

    Row(modifier = Modifier.fillMaxWidth().wrapContentHeight(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = "Choose a Name",
            fontSize = 28.sp,
            fontWeight = FontWeight.W900,
        )
    }

    Spacer(modifier = Modifier.height(40.dp))

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
                        Text("Enter your name", fontSize = 16.sp, color = Color.Gray)
                    it()
                }
            })
    }

    Spacer(modifier = Modifier.height(15.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)) // TextField background color
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 6.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "menu",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .padding(3.dp)
                .clip(CircleShape)
        )

        Text(
            text = "Add a Photo (optional)",
            fontSize = 16.sp,
            fontWeight = FontWeight.W900,
            style = TextStyle(color = Blue),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun Button( text : String, onClick: () -> Unit) {
    androidx.compose.material3.Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 65.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = ButtonDefaults.buttonColors(containerColor = Blue),
        shape = RoundedCornerShape(28.dp)
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

//@Serializable
//object Home