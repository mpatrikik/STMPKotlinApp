package com.example.stmpapp.data

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
    private set

    fun addTask(description: String) {
        tasks = tasks + Task(description = description)
    }

    fun toggleCompleted(taskId: String) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(completed = !it.completed)
             else it
        }
    }

    fun toggleSelection(taskId: String) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(selected = !it.selected)
            else it
        }
    }

    fun deleteSelected() {
        tasks = tasks.filter { !it.selected }
    }

    fun deleteTask(taskId: String) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(selected = !it.selected)
            else it
        }
    }
}