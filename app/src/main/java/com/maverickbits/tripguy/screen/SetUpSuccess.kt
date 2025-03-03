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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverickbits.tripguy.R
import com.maverickbits.tripguy.ui.theme.background

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun SetUpComplete() {
    Column(
        Modifier
            .fillMaxSize()
            .background(background)
            .statusBarsPadding(),
    ) {
        Toolbar(text = "")
        GreatJob()
        LoginDetailsCard()
        Spacer(modifier = Modifier.weight(1f,true))
        Button("Finish")
    }

}

@Composable
fun GreatJob() {
    Column(

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.cloudy),
                contentDescription = "cloud"
            )
            Image(
                painter = painterResource(R.drawable.route),
                contentDescription = "route"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Center the text horizontally
        ) {
            Text(
                text = "Great Job!",
                fontSize = 28.sp,
                fontWeight = FontWeight.W900,
                textAlign = TextAlign.Center // Ensures text itself is centered
            )
            Text(
                text = "You have set up your first expense log.",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp)
            )

        }
    }
}

@Composable
fun LoginDetailsCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "menu",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .padding(8.dp)
                    .clip(CircleShape)
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Name",
                fontSize = 13.sp,
                fontWeight = FontWeight.W600
            )
            Text(
                text = "Harsh Tiwari"
            )
        }
        Divider(
            color = background,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 20.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Home Currency",
                fontSize = 13.sp,
                fontWeight = FontWeight.W600

                )
            Text(
                text = "INR (RS)"
            )
        }
    }
}



