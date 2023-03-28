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
package com.peterchege.mywishlistapp.ui.bottomSheets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.peterchege.mywishlistapp.core.util.Constants
import com.peterchege.mywishlistapp.core.util.Constants.categoryList
import com.peterchege.mywishlistapp.core.util.Constants.priorityList
import com.peterchege.mywishlistapp.core.util.isNumeric
import com.peterchege.mywishlistapp.ui.components.MenuSample
import com.peterchege.mywishlistapp.ui.screens.wishlist_item.WishlistItemScreenViewModel

@Composable
fun EditItemBottomSheet(
    viewModel: WishlistItemScreenViewModel = hiltViewModel()

) {
    Column(
        modifier = Modifier
            .height(500.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        viewModel.editWishListItem.value?.let { wishlistItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Add a new wishlist Item ",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = MaterialTheme.colors.primary)
                )
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = wishlistItem.name,
                onValueChange = {
                    viewModel._editWishListItem.value = wishlistItem.copy(name = it)

                },
                label = {
                    Text(
                        text = "Name",
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                },

                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.primary,
                    focusedIndicatorColor = MaterialTheme.colors.surface,

                    ),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                TextField(
                    modifier = Modifier.width(200.dp),
                    value = wishlistItem.amount.toString(),
                    onValueChange = {text ->
                        if (text.isBlank()) {
                            viewModel._editWishListItem.value = wishlistItem.copy(amount = 0)
                            return@TextField
                        }
                        if (isNumeric(text)) {
                            viewModel._editWishListItem.value = wishlistItem.copy(amount = text.toInt())

                        }


                    },
                    label = {
                        Text(
                            text = "Amount",
                            style = TextStyle(color = MaterialTheme.colors.primary)
                        )
                    },

                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.primary,
                        focusedIndicatorColor = MaterialTheme.colors.surface,

                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
                )
                Spacer(
                    modifier = Modifier
                        .width(10.dp)

                )
                TextField(
                    modifier = Modifier.width(200.dp),
                    value = wishlistItem.quantity.toString(),
                    onValueChange = { text ->
                        if (text.isBlank()) {
                            viewModel._editWishListItem.value = wishlistItem.copy(quantity = 0)
                            return@TextField
                        }
                        if (isNumeric(text)) {
                            viewModel._editWishListItem.value = wishlistItem.copy(quantity = text.toInt())

                        }
                    },
                    label = {
                        Text(
                            text = "Quantity",
                            style = TextStyle(color = MaterialTheme.colors.primary)
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.primary,
                        focusedIndicatorColor = MaterialTheme.colors.surface,

                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Column() {
                    Text(
                        text = "Category",
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )

                    MenuSample(
                        selectedIndex = categoryList.indexOf(wishlistItem.category),
                        onChangeSelectedIndex = {
                            viewModel._editWishListItem.value = wishlistItem.copy(category = categoryList[it])
                        },
                        menuItems = categoryList,
                        menuWidth = 200,
                    )
                }
                Column() {
                    Text(
                        text = "Priority",
                        style = TextStyle(color = MaterialTheme.colors.primary)
                    )
                    MenuSample(
                        selectedIndex = priorityList.indexOf(wishlistItem.priority),
                        onChangeSelectedIndex = {
                            viewModel._editWishListItem.value = wishlistItem.copy(priority = priorityList[it])
                        },
                        menuItems = Constants.priorityList,
                        menuWidth = 200
                    )

                }

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface
                ),
                onClick = {
                    viewModel.editItem()
                }
            ) {
                Text(
                    text = "Save Changes",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = MaterialTheme.colors.primary
                    )
                )

            }
        }

    }
}