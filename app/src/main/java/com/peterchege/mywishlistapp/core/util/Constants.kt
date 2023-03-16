package com.peterchege.mywishlistapp.core.util

import androidx.datastore.preferences.core.stringPreferencesKey

object  Constants {
    const val LIGHT_MODE = "LIGHT_MODE"
    const val DARK_MODE = "DARK_MODE"

    val THEME_OPTIONS = stringPreferencesKey("theme_options")


}