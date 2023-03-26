/*
 * Copyright 2023 My Wishlist App By Peter Chege
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peterchege.mywishlistapp.core.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object  Constants {
    const val LIGHT_MODE = "LIGHT_MODE"
    const val DARK_MODE = "DARK_MODE"

    const val USER_PREFERENCES = "USER_PREFERENCES"

    val THEME_OPTIONS = stringPreferencesKey("theme_options")

    val IS_FIRST_TIME_LAUNCH = booleanPreferencesKey("is_first_time_launch")

    val priorityList = listOf("Long Term", "Short Term")
    val categoryList = listOf("Personal", "For friends And family")

    const val DATABASE_NAME = "wishlist.db"




}