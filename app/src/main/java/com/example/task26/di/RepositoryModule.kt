package com.example.task26.di

import com.example.task26.data.remote.repository.CategoriesRepositoryImpl
import com.example.task26.domain.remote.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindCategoriesRepository(repositoryImpl: CategoriesRepositoryImpl): CategoryRepository
}