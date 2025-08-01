package com.example.stmpapp.data

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class TaskRepository(private val context: Context) {

    private val fileName = "tasks.json"

    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    private fun getFile(): File {
        return File(context.filesDir, fileName)
    }

    fun loadTasks(): List<Task> {
        val file = getFile()
        if (!file.exists()) return emptyList()
        val jsonText = file.readText()
        return try {
            json.decodeFromString(jsonText)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun saveTasks(tasks: List<Task>) {
        val file = getFile()
        file.writeText(json.encodeToString(tasks))
    }
}
