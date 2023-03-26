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
package com.peterchege.mywishlistapp.core.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.peterchege.mywishlistapp.core.datastore.preferences.UserPreferences
import com.peterchege.mywishlistapp.core.room.database.WishlistAppDatabase
import com.peterchege.mywishlistapp.core.util.Constants
import com.peterchege.mywishlistapp.data.UserPreferenceRepositoryImpl
import com.peterchege.mywishlistapp.data.WishlistRepositoryImpl
import com.peterchege.mywishlistapp.domain.repository.UserPreferenceRepository
import com.peterchege.mywishlistapp.domain.repository.WishlistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWishListAppDatabase(
        app:Application
    ):WishlistAppDatabase {
        return Room.databaseBuilder(
            app,
            WishlistAppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatastorePreferences(@ApplicationContext context: Context):
            DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(name = Constants.USER_PREFERENCES)
            }
        )
    }

    @Provides
    @Singleton
    fun provideUserPreferences(dataStore: DataStore<Preferences>): UserPreferences {
        return UserPreferences(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(userPreferences: UserPreferences):
            UserPreferenceRepository {
        return UserPreferenceRepositoryImpl(
            preferences = userPreferences
        )
    }

    @Provides
    @Singleton
    fun provideWishListItemRepository(db:WishlistAppDatabase):
            WishlistRepository {
        return WishlistRepositoryImpl(db = db)
    }



}