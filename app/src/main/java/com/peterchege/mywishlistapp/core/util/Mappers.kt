package com.peterchege.mywishlistapp.core.util

import com.peterchege.mywishlistapp.core.models.WishListItem
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity


fun WishListItem.toEntity(): WishlistItemEntity {
    return WishlistItemEntity(
        itemId = itemId,
        name = name,
        amount = amount,
        quantity = quantity,
        category = category,
        priority = priority,
        createdAt = createdAt,
        imageUrl = imageUrl,
        isPurchased = isPurchased
    )

}

fun WishlistItemEntity.toExternalModel():WishListItem{
    return WishListItem(
        itemId = itemId,
        name = name,
        amount = amount,
        quantity = quantity,
        category = category,
        priority = priority,
        createdAt = createdAt,
        imageUrl = imageUrl,
        isPurchased = isPurchased
    )
}