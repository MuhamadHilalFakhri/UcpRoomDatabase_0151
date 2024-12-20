package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HalamanUtama(navController: NavController,
                 modifier: Modifier = Modifier){
    Column (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button(onClick = {
            navController.navigate("Dosen")
        }) {
            Text(text = "Dosen")
        }
        Button(onClick = {
            navController.navigate("MK")
        }) {
            Text(text = "Matakuliah")
        }
    }
}