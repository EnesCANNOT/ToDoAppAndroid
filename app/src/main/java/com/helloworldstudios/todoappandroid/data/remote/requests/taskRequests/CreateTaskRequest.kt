package com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests

data class CreateTaskRequest(
    val title: String,
    val description: String,
    val taskStatus: Long,
    val dueDate: String
)
