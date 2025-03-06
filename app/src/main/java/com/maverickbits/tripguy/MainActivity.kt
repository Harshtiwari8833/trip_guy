package com.maverickbits.tripguy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.maverickbits.tripguy.room.database.TripDatabase

import com.maverickbits.tripguy.screen.LoginScreen
import com.maverickbits.tripguy.screen.TripEntry
import com.maverickbits.tripguy.screen.TripListScreen
import com.maverickbits.tripguy.ui.theme.TripGuyTheme
import com.maverickbits.tripguy.veiwModel.TripViewModel
import com.maverickbits.tripguy.veiwModel.TripViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val GOOGLE_REQ_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Get TripDao instance (assuming you have a database instance)
        val tripDao = TripDatabase.getDatabase(this).tripDao()
        // Create ViewModel using Factory
        val viewModel: TripViewModel = ViewModelProvider(
            this, TripViewModelFactory(tripDao)
        )[TripViewModel::class.java]
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        setContent {
            TripGuyTheme {
                LoginScreen({signIn()})
            }
        }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_REQ_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                handleSignInResult(account)
            } catch (e: ApiException) {
                Toast.makeText(this, "Sign-in failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun handleSignInResult(account: GoogleSignInAccount?) {
        if (account != null) {
            val email = account.email
            val displayName = account.displayName

            val sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE).edit()
            sharedPreferences.putString("userEmail", email)
            sharedPreferences.putString("userName", displayName)
            sharedPreferences.putString("userImg", account.photoUrl.toString())
            sharedPreferences.putBoolean("isLoggedIn", true)
            sharedPreferences.apply()
            Toast.makeText(this, "Signed in as: $email", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Sign-in failed. Please try again.", Toast.LENGTH_SHORT).show()
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



