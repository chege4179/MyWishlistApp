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
package com.peterchege.mywishlistapp.ui.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peterchege.mywishlistapp.core.util.Screens
import com.peterchege.mywishlistapp.ui.screens.onboarding.OnboardingScreen
import com.peterchege.mywishlistapp.ui.screens.wishlist_item.WishListItemScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavigation(
    navHostController: NavHostController,
    viewModel: AppNavigationViewModel = hiltViewModel()
) {
    val isFirstTimeLaunch = viewModel.isFirstTimeLaunch.collectAsStateWithLifecycle().value
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(key1 = isFirstTimeLaunch){
        if (!isFirstTimeLaunch){
            navHostController.navigate(Screens.BOTTOM_TAB_NAVIGATION_WRAPPER)
        }
    }
    NavHost(
        navController = navHostController,
        startDestination = Screens.ONBOARDING_SCREEN,
    ) {
        composable(route = Screens.ONBOARDING_SCREEN) {
            OnboardingScreen(navController = navHostController)
        }
        composable(route = Screens.WISHLIST_ITEM_SCREEN + "/{id}") {
            WishListItemScreen(navController = navHostController)
        }
        composable(route = Screens.BOTTOM_TAB_NAVIGATION_WRAPPER) {
            BackHandler(enabled = true) {
                activity?.finish()

            }
            BottomTabNavigationWrapper(navHostController = navHostController)
        }
    }
}