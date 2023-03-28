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
package com.peterchege.mywishlistapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.peterchege.mywishlistapp.core.models.WishListItem
import com.peterchege.mywishlistapp.core.util.formatDecimalSeparator

@ExperimentalCoilApi
@Composable
fun WishlistItemCard(
    item: WishListItem,
    onNavigate: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()

            .padding(10.dp)
            .height(70.dp)

            .clickable {
                onNavigate(item.itemId)

            },
        shape = RoundedCornerShape(15),
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colors.onBackground)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp),
                painter = rememberImagePainter(
                    data = item.imageUrl,
                    builder = {
                        crossfade(true)
                    },
                ),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .width(140.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = MaterialTheme.colors.primary)

                )
                Text(
                    text = item.createdAt,
                    style = TextStyle(color = MaterialTheme.colors.primary)
                )

            }
            Text(
                text = "KES ${item.amount.formatDecimalSeparator()} /=",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = MaterialTheme.colors.primary)
            )
            IconButton(onClick = {
                onNavigate(item.itemId)
            }) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    tint = Color.Black,
                    contentDescription = "More "
                )

            }

        }

    }

}