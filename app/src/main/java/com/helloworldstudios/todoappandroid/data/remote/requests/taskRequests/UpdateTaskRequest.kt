package com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests

data class UpdateTaskRequest(
    val id: Long,
    val title: String,
    val description: String,
    val taskStatus: Long,
    val dueDate: String
)
