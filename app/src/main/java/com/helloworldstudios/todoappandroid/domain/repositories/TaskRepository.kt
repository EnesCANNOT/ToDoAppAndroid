package com.helloworldstudios.todoappandroid.domain.repositories

import com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests.CreateTaskRequest
import com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests.DeleteTaskRequest
import com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests.UpdateTaskRequest
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.CreatedTaskResponse
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.DeletedTaskResponse
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.GetTaskResponse
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.UpdatedTaskResponse
import com.helloworldstudios.todoappandroid.data.remote.services.TaskApi
import com.helloworldstudios.todoappandroid.util.Resource
import com.helloworldstudios.todoappandroid.util.Utils
import retrofit2.Response
import javax.inject.Inject

class TaskRepository @Inject constructor(val taskApi: TaskApi) {

    suspend fun createTask(request: CreateTaskRequest): Resource<CreatedTaskResponse> {
        return safeApiCall(
            apiCall = { taskApi.createTask(request) },
            successMessage = "Task successfully created."
        )
    }

    suspend fun updateTask(request: UpdateTaskRequest): Resource<UpdatedTaskResponse> {
        return safeApiCall(
            apiCall = { taskApi.updateTask(request) },
            successMessage = "Task successfully updated."
        )
    }

    suspend fun deleteTask(request: DeleteTaskRequest): Resource<DeletedTaskResponse> {
        return safeApiCall(
            apiCall = { taskApi.deleteTask(request) },
            successMessage = "Task successfully deleted."
        )
    }

    suspend fun getAllTasks(): Resource<List<GetTaskResponse>>{
        return safeApiCall(
            apiCall = {taskApi.getAll()},
            successMessage = "Tasks successfully fetched."
        )
    }

    private suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>,
        successMessage: String
    ): Resource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    Resource.Success(data = responseBody, message = successMessage, httpCode = response.code().toString())
                } else {
                    Resource.Error(data = null, message = "Response body is null.", httpCode = response.code().toString())
                }
            } else {
                val errorBodyString = response.errorBody()?.string() ?: "Empty error body."
                val errorMessage = Utils.extractDetailFromErrorBody(errorBodyString)
                    ?: "Unknown error occurred. HTTP ${response.code()} - ${response.message()}"

                Resource.Error(data = null, message = errorMessage, httpCode = response.code().toString())
            }
        } catch (e: Exception) {
            Resource.Error(data = null, message = e.localizedMessage ?: "An unknown error occurred.", httpCode = null)
        }
    }
}