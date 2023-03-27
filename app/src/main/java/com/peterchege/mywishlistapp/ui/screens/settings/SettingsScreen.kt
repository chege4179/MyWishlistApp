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
package com.peterchege.mywishlistapp.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.peterchege.mywishlistapp.core.util.Constants
import com.peterchege.mywishlistapp.ui.components.SettingsRow
import com.peterchege.mywishlistapp.ui.screens.wishlist_item.TopRightIcons

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel:SettingsScreenViewModel = hiltViewModel()

) {
    val theme = viewModel.theme.collectAsStateWithLifecycle(initialValue = Constants.LIGHT_MODE)
    val context = LocalContext.current
    Scaffold(
        modifier= Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.onBackground,
                title = {
                    Text(
                        style = TextStyle(color = MaterialTheme.colors.primary),
                        text = "Settings",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },
            )
        },
    ) {
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SettingsRow(
                title = "Dark Theme",
                checked =  theme.value == Constants.DARK_MODE,
                onCheckedChange ={
                    if (it){
                        viewModel.updateTheme(themeValue = Constants.DARK_MODE)
                    }else{
                        viewModel.updateTheme(themeValue = Constants.LIGHT_MODE)
                    }
                }
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))
            SettingsRow(
                title = "Enable Notifications",
                checked =  false,
                onCheckedChange ={

                }
            )

        }

    }

}