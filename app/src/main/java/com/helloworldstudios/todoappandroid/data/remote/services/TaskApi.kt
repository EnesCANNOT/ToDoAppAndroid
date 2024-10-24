package com.helloworldstudios.todoappandroid.data.remote.services

import com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests.CreateTaskRequest
import com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests.DeleteTaskRequest
import com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests.UpdateTaskRequest
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.CreatedTaskResponse
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.DeletedTaskResponse
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.UpdatedTaskResponse
import com.helloworldstudios.todoappandroid.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskApi {
    @POST("${Constants.TASK_API}create")
    suspend fun createTask(@Body request: CreateTaskRequest):Response<CreatedTaskResponse>

    @POST("${Constants.TASK_API}update")
    suspend fun updateTask(@Body request: UpdateTaskRequest): Response<UpdatedTaskResponse>

    @POST("${Constants.TASK_API}delete")
    suspend fun deleteTask(@Body request: DeleteTaskRequest): Response<DeletedTaskResponse>
}