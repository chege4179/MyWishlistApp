package com.peterchege.mywishlistapp.core.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object  Constants {
    const val LIGHT_MODE = "LIGHT_MODE"
    const val DARK_MODE = "DARK_MODE"

    const val USER_PREFERENCES = "USER_PREFERENCES"

    val THEME_OPTIONS = stringPreferencesKey("theme_options")

    val IS_FIRST_TIME_LAUNCH = booleanPreferencesKey("is_first_time_launch")




}