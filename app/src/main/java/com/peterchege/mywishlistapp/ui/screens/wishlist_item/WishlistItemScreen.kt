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
package com.peterchege.mywishlistapp.ui.screens.wishlist_item

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.peterchege.mywishlistapp.core.util.BottomSheets
import com.peterchege.mywishlistapp.core.util.UiEvent
import com.peterchege.mywishlistapp.ui.bottomSheets.DeleteItemBottomSheet
import com.peterchege.mywishlistapp.ui.bottomSheets.EditItemBottomSheet
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WishListItemScreen(
    navController: NavController,
    viewModel: WishlistItemScreenViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = viewModel.activeBottomSheet.value){
        if (viewModel.activeBottomSheet.value !== null){
            modalSheetState.show()
        }

    }
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
                    modalSheetState.hide()
                }

                else -> {

                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = MaterialTheme.colors.onBackground,

        sheetContent = {
            when(viewModel.activeBottomSheet.value){
                BottomSheets.EDIT_ITEM -> {
                    EditItemBottomSheet()
                }
                BottomSheets.DELETE_ITEM -> {
                    DeleteItemBottomSheet()
                }
                else -> {}
            }

        }
    ){
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    title = {
                        viewModel.wishListItem.value?.let {
                            Text(
                                style = TextStyle(color = MaterialTheme.colors.primary),
                                text = it.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        }

                    },
                    actions = {
                        TopRightIcons()
                    }
                )
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(color = MaterialTheme.colors.background)
            ) {
                viewModel.wishListItem.value?.let { item ->
                    Text(
                        text = "Item ID : ${item.itemId}",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                    Text(
                        text = "Item Name : ${item.name}",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                    Text(
                        text = "Item Amount : KES ${item.amount}",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                    Text(
                        text = "Item Category : ${item.category}",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                    Text(
                        text = "Created On : ${item.createdAt}",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                    Text(
                        text = "Priority : ${item.priority}",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                }
            }

        }
    }


}

@Composable
fun RowScope.TopRightIcons(
    viewModel: WishlistItemScreenViewModel = hiltViewModel()
){
    IconButton(
        onClick = {
            viewModel.onChangeActiveBottomSheet(BottomSheets.EDIT_ITEM)

        }) {
        Icon(
            modifier = Modifier
                .size(35.dp)
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .background(MaterialTheme.colors.onBackground)
                .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
            imageVector = Icons.Default.Edit,
            contentDescription = null,
            tint = MaterialTheme.colors.surface
        )
    }
    IconButton(
        onClick = {
            viewModel.onChangeActiveBottomSheet(BottomSheets.DELETE_ITEM)
        }) {
        Icon(
            modifier = Modifier
                .size(35.dp)
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .background(MaterialTheme.colors.onBackground)
                .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = MaterialTheme.colors.surface
        )
    }
}