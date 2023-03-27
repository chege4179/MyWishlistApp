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
package com.peterchege.mywishlistapp.ui.screens.wishlist_item

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.mywishlistapp.core.models.WishListItem
import com.peterchege.mywishlistapp.core.util.BottomSheets
import com.peterchege.mywishlistapp.core.util.Screens
import com.peterchege.mywishlistapp.core.util.UiEvent
import com.peterchege.mywishlistapp.core.util.toExternalModel
import com.peterchege.mywishlistapp.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject



@HiltViewModel
class WishlistItemScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: WishlistRepository

) : ViewModel() {

    val _wishListItem = mutableStateOf<WishListItem?>(null)
    val wishListItem: State<WishListItem?> = _wishListItem

    val _activeBottomSheet = mutableStateOf<BottomSheets?>(null)
    val activeBottomSheet:State<BottomSheets?> = _activeBottomSheet

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onChangeActiveBottomSheet(bottomSheet:BottomSheets){
        _activeBottomSheet.value = bottomSheet
    }

    init {
        savedStateHandle.get<String>("id")?.let {id ->
            viewModelScope.launch {
                try {
                    val item = repository.getWishListItemById(itemId = id)?.toExternalModel()
                    _wishListItem.value = item
                }catch (e:Exception){

                    Timber.d(e.localizedMessage)
                }

            }

        }
    }

    fun deleteWishListItem(id:String){
        viewModelScope.launch {
            try {
                repository.deleteWishListItemById(itemId = id)
                _eventFlow.emit(UiEvent.Navigate(route = Screens.BOTTOM_TAB_NAVIGATION_WRAPPER))
            }catch (e:Exception){
                Timber.d(e.localizedMessage)

            }
        }
    }
}
