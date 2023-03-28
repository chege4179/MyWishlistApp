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
package com.peterchege.mywishlistapp.core.room.dao

import androidx.room.*
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface WishListItemEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistItem(wishlistItemEntity: WishlistItemEntity)

    @Query("SELECT * FROM wishlist")
    fun getAllWishlistItems(): Flow<List<WishlistItemEntity>>

    @Query("SELECT * FROM wishlist WHERE itemId = :itemId")
    suspend fun getWishListItemById(itemId: String): WishlistItemEntity?

    @Query("DELETE FROM wishlist ")
    suspend fun deleteAllWishListItems()

    @Query("DELETE  FROM wishlist WHERE itemId = :itemId")
    suspend fun deleteWishListItemById(itemId: String)

    @Query("UPDATE wishlist  SET name= :name ,amount = :amount, quantity = :quantity , priority = :priority ,category = :category WHERE itemId = :itemId")
    suspend fun updateWishListItemById(
        itemId: String, name: String, amount: Int,
        quantity: Int, category: String, priority: String
    )


}