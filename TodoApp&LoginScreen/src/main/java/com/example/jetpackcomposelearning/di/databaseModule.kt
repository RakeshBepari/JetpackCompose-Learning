package com.example.jetpackcomposelearning.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposelearning.data.TodoDatabase
import com.example.jetpackcomposelearning.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context:Context
    ) = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesTodoDao(
        database: TodoDatabase
    ) = database.getTodoDao()
}