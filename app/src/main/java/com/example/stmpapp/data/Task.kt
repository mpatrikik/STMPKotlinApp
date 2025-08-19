package com.example.stmpapp.data

data class Task(
    val id: String = java.util.UUID.randomUUID().toString(),
    val description: String,
    var isCompleted: Boolean = false,
    var isSelected: Boolean = false
)

