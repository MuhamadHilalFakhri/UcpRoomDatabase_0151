package com.example.ucp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.navigation.PengelolaHalamanDosen
import com.example.ucp2.ui.navigation.PengelolaHalamanMK
import com.example.ucp2.ui.theme.UCP2Theme
import com.example.ucp2.ui.view.HalamanUtama

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UCP2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "Utama",
                        modifier = Modifier.padding(innerPadding) //
                    ) {
                        composable("Utama") {
                            HalamanUtama(navController)
                        }
                        composable("Dosen") {
                            PengelolaHalamanDosen()
                        }
                        composable("MK") {
                            PengelolaHalamanMK()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UCP2Theme {
        Greeting("Android")
    }
}