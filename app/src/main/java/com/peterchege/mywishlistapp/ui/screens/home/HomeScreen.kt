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
package com.peterchege.mywishlistapp.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.peterchege.mywishlistapp.core.util.Screens
import com.peterchege.mywishlistapp.core.util.TestTags
import com.peterchege.mywishlistapp.core.util.UiEvent
import com.peterchege.mywishlistapp.core.util.toExternalModel
import com.peterchege.mywishlistapp.ui.bottomSheets.AddItemBottomSheet
import com.peterchege.mywishlistapp.ui.components.WishlistItemCard
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val searchText = viewModel.searchText.collectAsStateWithLifecycle()
    val isSearching = viewModel.isSearching.collectAsStateWithLifecycle()

    val wishListItems = viewModel.searchResults
        .collectAsStateWithLifecycle()
        .value
        .map { it.toExternalModel() }


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

                else -> {}
            }
        }
    }
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = MaterialTheme.colors.onBackground,

        sheetContent = {
            AddItemBottomSheet()
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colors.background)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically,
                ) {
                    Text(
                        text = "Wishlist ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        style = TextStyle(color = MaterialTheme.colors.primary),
                        modifier = Modifier.testTag(tag = TestTags.HOME_SCREEN_HEADER)
                    )
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = CenterVertically
                ) {
                    TextField(
                        value = searchText.value,
                        onValueChange = viewModel::onSearchTextChange,
                        placeholder = {
                            Text(
                                text = "Search",
                                style = TextStyle(color = MaterialTheme.colors.primary)
                            )
                        },

                        modifier = Modifier
                            .fillMaxWidth(0.80f)
                            .background(
                                MaterialTheme.colors.onBackground,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .testTag(tag = TestTags.HOME_SCREEN_SEARCH_BAR)
                        ,
                        shape = RoundedCornerShape(size = 8.dp),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.primary,
                            disabledTextColor = MaterialTheme.colors.primary,
                            backgroundColor = MaterialTheme.colors.onBackground,
                            focusedIndicatorColor = MaterialTheme.colors.background,
                            unfocusedIndicatorColor = MaterialTheme.colors.background,
                            disabledIndicatorColor = MaterialTheme.colors.background
                        ),
                        textStyle = TextStyle(color = MaterialTheme.colors.primary),
                        maxLines = 1,
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Product",
                                modifier = Modifier.size(25.dp)

                            )
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                            .background(MaterialTheme.colors.onBackground)
                    )
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                if (modalSheetState.isVisible) {
                                    modalSheetState.hide()
                                } else {
                                    modalSheetState.show()
                                }

                            }
                        }) {
                        Icon(
                            modifier = Modifier
                                .size(55.dp)
                                .clip(shape = RoundedCornerShape(size = 8.dp))
                                .background(MaterialTheme.colors.onBackground)
                                .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                                .testTag(tag = TestTags.HOME_SCREEN_ADD_ITEM_ICON)
                            ,
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colors.surface
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .background(MaterialTheme.colors.onBackground)
                )
                Text(
                    text = "Your Wishlist",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    style = TextStyle(color = MaterialTheme.colors.primary)
                )
                if (isSearching.value) {
                    Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.surface,
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.Center)
                        )
                    }

                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(items = wishListItems) { item ->
                        WishlistItemCard(
                            item = item,
                            onNavigate = {
                                navHostController.navigate(
                                    route = Screens.WISHLIST_ITEM_SCREEN + "/${it}"
                                )

                            })
                    }
                }

            }

        }
    }


}