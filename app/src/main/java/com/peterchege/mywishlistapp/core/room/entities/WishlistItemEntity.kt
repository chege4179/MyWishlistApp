package com.peterchege.mywishlistapp.core.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wishlist")
data class WishlistItemEntity(
    @PrimaryKey
    val itemId:String,
    val name: String,
    val amount: Int,
    val quantity: Int,
    val category:String,
    val priority:String,
    val createdAt:String,
    val imageUrl:String,
    val isPurchased:Boolean = false,
)