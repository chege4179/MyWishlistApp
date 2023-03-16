package com.peterchege.mywishlistapp.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.peterchege.mywishlistapp.core.util.Screens
import com.peterchege.mywishlistapp.ui.screens.wishlist_item.WishlistItemScreenViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: WishlistItemScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier= Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.background,
                onClick = {
                    navController.navigate(Screens.CREATE_WISHLIST_ITEM_SCREEN)
                }
            ){

            }
        },

    ) {
        Column(
            modifier= Modifier.fillMaxSize()
        ) {
            Text(
                text = "Home Screen"
            )

        }

    }

}