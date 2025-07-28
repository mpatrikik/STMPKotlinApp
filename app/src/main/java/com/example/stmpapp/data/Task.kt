package com.example.stmpapp.data

data class Task(
    val id: String = java.util.UUID.randomUUID().toString(),
    val description: String,
    val completed: Boolean = false,
    val selected: Boolean = false
)

