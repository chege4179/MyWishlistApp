package com.peterchege.mywishlistapp.domain.repostory

import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {

    suspend fun setTheme(themeValue:String)

    fun getTheme(): Flow<String>

    suspend fun setIsFirstTimeLaunch()

    fun getIsFirstTimeLaunch(): Flow<Boolean>


}