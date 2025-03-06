package com.maverickbits.tripguy.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.maverickbits.tripguy.R
import com.maverickbits.tripguy.ui.theme.background
import com.maverickbits.tripguy.ui.theme.loginbg


@Composable
fun LoginScreen(onclick: ()-> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(background)
            .statusBarsPadding()
    ) {
        ToolbarCancel()
        Spacer(modifier = Modifier.height(20.dp))
        Body()
        Spacer(modifier = Modifier.weight(1f,true))
        Button("Login With Google" ,
            {onclick()})
    }

}

@Composable
fun ToolbarCancel() {
    Text(
        text = "Cancel",
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(start = 10.dp)
            .padding(20.dp)
    )
}
@Composable
fun Body() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome Back! Sign in with Google",
                fontSize = 32.sp,
                fontWeight = FontWeight.W900,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(60.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 40.dp)
                .background(loginbg, shape = RoundedCornerShape(8.dp)) // TextField background color


        ) {
            Image(
                painter = painterResource(
                    R.drawable.login,
                ),
                contentDescription = "Login sticker",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Sign in securely with your Google account—quick, easy, and password-free!",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                modifier =  Modifier.padding(horizontal = 40.dp, vertical =  20.dp)
            )
        }

    }


}



