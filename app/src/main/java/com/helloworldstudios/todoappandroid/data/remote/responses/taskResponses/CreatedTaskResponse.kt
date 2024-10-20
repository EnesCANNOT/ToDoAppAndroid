package com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses

import java.util.Date

data class CreatedTaskResponse(
    val id: Long,
    val title: String,
    val description: String,
    val taskStatus: Long,
    val dueDate: Date
)