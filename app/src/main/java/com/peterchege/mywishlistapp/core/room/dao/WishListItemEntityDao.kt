package com.peterchege.mywishlistapp.core.room.dao

import androidx.room.*
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface WishListItemEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistItem(wishlistItemEntity: WishlistItemEntity)

    @Query("SELECT * FROM wishlist")
    fun getAllWishlistItems():Flow<List<WishlistItemEntity>>

    @Query("SELECT * FROM wishlist WHERE itemId = :itemId")
    fun getWishListItemById(itemId:String):WishlistItemEntity?

    @Query("DELETE FROM wishlist ")
    suspend fun deleteAllWishListItems()

    @Query("DELETE  FROM wishlist WHERE itemId = :itemId")
    fun deleteWishListItemById(itemId:String)



}