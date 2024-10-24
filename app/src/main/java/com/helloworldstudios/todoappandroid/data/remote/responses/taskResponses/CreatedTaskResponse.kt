package com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses

data class CreatedTaskResponse(
    val id: Long,
    val title: String,
    val description: String,
    val taskStatus: Long,
    val dueDate: String
)