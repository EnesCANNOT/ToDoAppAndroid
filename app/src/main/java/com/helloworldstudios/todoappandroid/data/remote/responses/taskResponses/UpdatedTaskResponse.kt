package com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses

import java.time.LocalDateTime

data class UpdatedTaskResponse(
    val id: Long,
    val title: String,
    val description: String,
    val taskStatus: Long,
    val dueDate: LocalDateTime,
)
