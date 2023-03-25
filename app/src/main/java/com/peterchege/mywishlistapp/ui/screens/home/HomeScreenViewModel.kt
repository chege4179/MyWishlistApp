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

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

) :ViewModel(){

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

    fun onChangeItemAmount(text:Int){
        _itemAmount.value = text

    }
    fun onChangeItemQuantity(text:Int){
        _itemQuantity.value = text

    }
    fun onChangeCategoryIndex(text:Int){
        _selectedCategoryIndex.value = text

    }
    fun onChangePriorityIndex(text:Int){
        _selectedPriorityIndex.value = text

    }


}