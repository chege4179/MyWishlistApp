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
package com.peterchege.mywishlistapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.peterchege.mywishlistapp.core.room.entities.WishlistItemEntity
import com.peterchege.mywishlistapp.domain.repository.WishlistRepository
import com.peterchege.mywishlistapp.ui.screens.home.HomeScreenViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject :HomeScreenViewModel

    private  var mockRepo = mockk<WishlistRepository>()

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        val mockItems = mockk<Flow<List<WishlistItemEntity>>>()
        every { mockRepo.getAllWishlistItems() } returns  mockItems
        subject = HomeScreenViewModel(repository = mockRepo)


    }


    @Test
    fun `Verify wishlist items are fetch on instantiation`()= runTest {
        subject

        verify { mockRepo.getAllWishlistItems() }

    }
}