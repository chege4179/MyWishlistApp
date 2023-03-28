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
package com.peterchege.mywishlistapp.ui.screens.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.peterchege.mywishlistapp.R
import com.peterchege.mywishlistapp.core.util.UiEvent
import com.peterchege.mywishlistapp.ui.components.OnBoardingCard
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText
                    )
                }
                is UiEvent.Navigate -> {
                    navController.navigate(route = event.route)
                }
                is UiEvent.CloseBottomSheet -> {

                }
                
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome to My Wishlist App",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 42.sp,
                    style = TextStyle(color = MaterialTheme.colors.primary),
                    textAlign = TextAlign.Center,

                    )
                OnBoardingCard(
                    header = "The perfect wishlist",
                    description = "My Wishlist App is an app that allows you to track things " +
                            "that you'd like to get for your loved ones",
                    image = R.drawable.heart
                )
                OnBoardingCard(
                    header = "Search or Add Directly",
                    description = "You can add your items to your wishlist and search for them whenever",
                    image = R.drawable.search
                )
                OnBoardingCard(
                    header = "The perfect wishlist",
                    description = "My Wishlist App is an app that allows you to track things " +
                            "that you'd like to get for your loved ones",
                    image = R.drawable.analytics
                )
            }


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                ,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface
                ),
                onClick = {
                    viewModel.onProceed()

                }
            ) {
                Text(
                    text = "Continue",
                    style = TextStyle(
                        color = MaterialTheme.colors.primary
                    )
                )

            }


        }

    }

}