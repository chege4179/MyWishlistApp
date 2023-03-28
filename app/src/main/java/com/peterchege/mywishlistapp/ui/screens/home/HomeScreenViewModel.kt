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
package com.peterchege.mywishlistapp.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.mywishlistapp.core.models.WishListItem
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity
import com.peterchege.mywishlistapp.core.util.*
import com.peterchege.mywishlistapp.core.util.Constants.categoryList
import com.peterchege.mywishlistapp.core.util.Constants.priorityList
import com.peterchege.mywishlistapp.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: WishlistRepository,

) :ViewModel(){

    val items = repository.getAllWishlistItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    val searchResults = searchText
        .onEach { _isSearching.update { true } }
        .combine(items) { text, items ->
            if(text.isBlank()) {
                items
            } else {
                delay(500L)
                items.filter {
                    it.toExternalModel().doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            items.value
        )

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val _itemName = mutableStateOf("")
    val itemName: State<String> = _itemName

    val _itemAmount = mutableStateOf(0)
    val itemAmount: State<Int> = _itemAmount

    val _itemQuantity = mutableStateOf(0)
    val itemQuantity: State<Int> = _itemQuantity

    val _selectedCategoryIndex = mutableStateOf(0)
    val selectedCategoryIndex: State<Int> = _selectedCategoryIndex

    val _selectedPriorityIndex = mutableStateOf(0)
    val selectedPriorityIndex: State<Int> = _selectedPriorityIndex


    fun onChangeItemName(text:String){
        _itemName.value = text
    }

    fun onChangeItemAmount(text:String){
        if (text.isBlank()) {
            _itemAmount.value = 0
            return
        }
        if (isNumeric(text)) {
            _itemAmount.value = text.toInt()

        } else {
            _itemAmount.value = 0
        }

    }
    fun onChangeItemQuantity(text:String){
        if (text.isBlank()) {
            _itemQuantity.value = 0
            return
        }
        if (isNumeric(text)) {
            _itemQuantity.value = text.toInt()

        } else {
            _itemQuantity.value = 0
        }

    }
    fun onChangeCategoryIndex(text:Int){
        _selectedCategoryIndex.value = text

    }
    fun onChangePriorityIndex(text:Int){
        _selectedPriorityIndex.value = text

    }

    fun saveItem(){
        viewModelScope.launch {
            if (_itemName.value == ""){
                _eventFlow.emit(UiEvent.ShowSnackbar(uiText = "Name is required"))
                return@launch
            }
            if (_itemAmount.value == 0){
                _eventFlow.emit(UiEvent.ShowSnackbar(uiText = "Amount is required"))
                return@launch
            }
            if (_itemQuantity.value == 0){
                _eventFlow.emit(UiEvent.ShowSnackbar(uiText = "Quantity is required"))
                return@launch
            }
            val createdAt = if (Build.VERSION.SDK_INT >= 26) generateFormatDate(date = LocalDate.now())
            else ""
            val newItem =  WishListItem(
                itemId = UUID.randomUUID().toString(),
                name = _itemName.value,
                amount = _itemAmount.value,
                quantity = _itemQuantity.value,
                category = categoryList[_selectedCategoryIndex.value],
                priority = priorityList[_selectedPriorityIndex.value],
                createdAt = createdAt,
                isPurchased = false,
                imageUrl = generateAvatarURL(name = _itemName.value)
            )
            try {
                repository.insertWishListItem(item = newItem)
                resetInput()
                _eventFlow.emit(UiEvent.ShowSnackbar(uiText = "Item saved successfully"))
            }catch (e:Exception){
                _eventFlow.emit(UiEvent.ShowSnackbar(uiText = "An unexpected error occurred"))

            }
        }
    }

    private fun resetInput(){
        _itemAmount.value = 0
        _itemName.value = ""
        _itemQuantity.value = 0
        _selectedCategoryIndex.value = 0
        _selectedPriorityIndex.value = 0

    }


}