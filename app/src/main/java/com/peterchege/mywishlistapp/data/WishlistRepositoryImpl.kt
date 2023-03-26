package com.peterchege.mywishlistapp.data

import com.peterchege.mywishlistapp.core.models.WishListItem
import com.peterchege.mywishlistapp.core.room.database.WishlistAppDatabase
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity
import com.peterchege.mywishlistapp.core.util.toEntity
import com.peterchege.mywishlistapp.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistRepositoryImpl @Inject constructor(
    private val db:WishlistAppDatabase
):WishlistRepository {
    override fun getAllWishlistItems(): Flow<List<WishlistItemEntity>> {
        return db.wishlistItemEntityDao.getAllWishlistItems()
    }

    override suspend fun insertWishListItem(item: WishListItem) {
        return db.wishlistItemEntityDao.insertWishlistItem(wishlistItemEntity = item.toEntity())
    }

    override suspend fun getWishListItemById(itemId: String): WishlistItemEntity? {
        return db.wishlistItemEntityDao.getWishListItemById(itemId = itemId)
    }
}