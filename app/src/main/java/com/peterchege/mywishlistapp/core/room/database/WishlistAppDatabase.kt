package com.peterchege.mywishlistapp.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peterchege.mywishlistapp.core.room.dao.WishListItemEntityDao
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity

@Database(
    entities = [
        WishlistItemEntity::class,

    ],
    version = 1,
    exportSchema = true
)
abstract class WishlistAppDatabase : RoomDatabase() {

    abstract val wishlistItemEntityDao: WishListItemEntityDao



}