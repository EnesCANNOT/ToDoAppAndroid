package com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests

import java.util.Date

data class CreateTaskRequest(
    val title: String,
    val description: String,
    val taskStatus: Long,
    val dueDate: Date
)
