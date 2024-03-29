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
package com.peterchege.mywishlistapp.core.datastore.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.peterchege.mywishlistapp.core.util.Constants
import com.peterchege.mywishlistapp.core.util.Constants.IS_FIRST_TIME_LAUNCH
import com.peterchege.mywishlistapp.core.util.Constants.THEME_OPTIONS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    fun getIsFirstTimeLaunch(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_FIRST_TIME_LAUNCH] ?: true
        }
    }
    suspend fun setIsFirstTimeLaunch(){
        dataStore.edit { preferences ->
            preferences[IS_FIRST_TIME_LAUNCH] = false
        }
    }

    suspend fun setTheme(themeValue: String) {
        dataStore.edit { preferences ->
            preferences[THEME_OPTIONS] = themeValue
        }
    }

    fun getTheme(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[THEME_OPTIONS] ?: Constants.LIGHT_MODE
        }
    }

}