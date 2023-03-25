package com.peterchege.mywishlistapp.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.mywishlistapp.domain.repostory.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppNavigationViewModel @Inject constructor(
    private val repository: UserPreferenceRepository
):ViewModel() {

    val isFirstTimeLaunch = repository.getIsFirstTimeLaunch()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = true
        )


}