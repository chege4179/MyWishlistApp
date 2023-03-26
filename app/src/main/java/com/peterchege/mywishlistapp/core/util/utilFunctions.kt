package com.peterchege.mywishlistapp.core.util

import java.util.*

fun generateAvatarURL(name:String):String{
    val splitname = name.split(" ").joinToString("+")
    val color = randomColorCode()
    return "https://ui-avatars.com/api/?background=${color}&color=fff&name=${splitname}&bold=true&fontsize=0.6&rounded=true"

}

fun randomColorCode(): String {
    val random = Random()
    val nextInt = random.nextInt(0xffffff + 1)
    return String.format("#%06x", nextInt).drop(1).capitalize(Locale.ROOT)
}

fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}
