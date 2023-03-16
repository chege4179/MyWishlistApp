package com.peterchege.mywishlistapp.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,

) {
    Scaffold(
        modifier= Modifier.fillMaxSize(),

    ) {
        Column(
            modifier= Modifier.fillMaxSize()
        ) {
            Text(
                text = "Settings Screen"
            )

        }

    }

}