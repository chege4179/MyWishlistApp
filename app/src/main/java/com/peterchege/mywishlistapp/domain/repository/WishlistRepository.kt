package com.peterchege.mywishlistapp.domain.repository

import com.peterchege.mywishlistapp.core.models.WishListItem
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {

    fun getAllWishlistItems(): Flow<List<WishlistItemEntity>>

    suspend fun insertWishListItem(item:WishListItem)

    suspend fun getWishListItemById(itemId:String):WishlistItemEntity?


}