package com.example.stmpapp.data

data class Task(
    val id: String = java.util.UUID.randomUUID().toString(),
    val description: String,
    val isCompleted: Boolean = false,
    val isSelected: Boolean = false
)

