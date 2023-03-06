/*
 * Copyright 2023 My Wishlist App By Peter Chege
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peterchege.mywishlistapp.ui.screens.wishlist_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.peterchege.mywishlistapp.core.util.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WishListScreen(
    navController: NavController,
    viewModel: WishlistScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier= Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.background,
                onClick = {
                    navController.navigate(Screens.CREATE_WISHLIST_ITEM_SCREEN)
                }

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null
                )
            }

        }
    ) {
        Column(
            modifier= Modifier.fillMaxSize()
        ) {
            Text(
                text = "WishList Item Screen"
            )

        }

    }

}