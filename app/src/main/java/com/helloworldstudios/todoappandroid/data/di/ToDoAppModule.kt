package com.helloworldstudios.todoappandroid.data.di

import com.helloworldstudios.todoappandroid.data.remote.services.TaskApi
import com.helloworldstudios.todoappandroid.domain.repositories.TaskRepository
import com.helloworldstudios.todoappandroid.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToDoAppModule {
    @Singleton
    @Provides
    fun retrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun injectTaskApi(retrofitInstance: Retrofit): TaskApi = retrofitInstance.create(TaskApi::class.java)

    @Singleton
    @Provides
    fun injectTaskRepository(): TaskRepository = TaskRepository(injectTaskApi(retrofitInstance()))
}