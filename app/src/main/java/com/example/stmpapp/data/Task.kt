package com.example.stmpapp.data

import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class Task(
    val id: String = UUID.randomUUID().toString(),
    val description: String,
    val isCompleted: Boolean = false,
    val isSelected: Boolean = false
)