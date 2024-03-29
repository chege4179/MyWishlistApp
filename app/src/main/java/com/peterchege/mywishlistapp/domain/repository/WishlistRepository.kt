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
package com.peterchege.mywishlistapp.domain.repository

import com.peterchege.mywishlistapp.core.models.WishListItem
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {

    fun getAllWishlistItems(): Flow<List<WishlistItemEntity>>

    suspend fun insertWishListItem(item:WishListItem)

    suspend fun getWishListItemById(itemId:String):WishlistItemEntity?

    suspend fun deleteWishListItemById(itemId:String)

    suspend fun updateWishListItemById(
        itemId: String, name: String, amount: Int,
        quantity: Int, category: String, priority: String
    )


}