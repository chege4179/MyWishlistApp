package com.peterchege.mywishlistapp.data

import androidx.datastore.preferences.core.Preferences
import com.peterchege.mywishlistapp.core.datastore.preferences.UserPreferences
import com.peterchege.mywishlistapp.domain.repostory.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferenceRepositoryImpl @Inject constructor(
    private val preferences: UserPreferences

):UserPreferenceRepository {
    override suspend fun setTheme(themeValue: String) {
        return preferences.setTheme(themeValue = themeValue)
    }

    override fun getTheme(): Flow<String> {
        return preferences.getTheme()
    }

    override suspend fun setIsFirstTimeLaunch() {
        return preferences.setIsFirstTimeLaunch()
    }

    override fun getIsFirstTimeLaunch(): Flow<Boolean> {
        return preferences.getIsFirstTimeLaunch()
    }

}