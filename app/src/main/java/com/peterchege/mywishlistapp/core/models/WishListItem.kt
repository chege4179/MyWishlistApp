package com.peterchege.mywishlistapp.core.models

data class WishListItem(
    val name: String,
    val amount: Int,
    val quantity: Int,
    val category:String,
    val priority:String,
    val periodTerm:String,
    val createdAt:String,
    val isPurchased:String,

    )