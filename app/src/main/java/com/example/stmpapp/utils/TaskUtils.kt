package com.example.stmpapp.utils

object TaskUtils {
    fun generateRandomTaskId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}