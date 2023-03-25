package com.peterchege.mywishlistapp.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.mywishlistapp.domain.repostory.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
    private val repository: UserPreferenceRepository
) :ViewModel(){


    fun onProceed(){
        viewModelScope.launch {
            repository.setIsFirstTimeLaunch()
        }

    }


}