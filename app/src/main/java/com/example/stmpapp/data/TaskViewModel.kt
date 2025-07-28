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
            if (it.id == taskId) it.copy(isCompleted = !it.isCompleted)
             else it
        }
    }

    fun toggleSelection(taskId: String) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(isSelected = !it.isSelected)
            else it
        }
    }

    fun deleteSelected() {
        tasks = tasks.filter { !it.isSelected }
    }

    fun markSelected(completed: Boolean) {
        tasks = tasks.map {
            if (it.isSelected) it.copy(isCompleted = completed)
            else it
        }
    }

}