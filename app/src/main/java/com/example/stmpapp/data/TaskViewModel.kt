package com.example.stmpapp.data

import TaskRepository
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
    private set

    init {
        viewModelScope.launch {
            tasks = repository.getTasks()
        }
    }

    fun addTask(description: String) {
        tasks = tasks + Task(description = description)
        saveTasks()
    }

    fun toggleCompleted(taskId: String) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(isCompleted = !it.isCompleted)
             else it
        }
        saveTasks()
    }

    fun deleteTask(taskId: String) {
        tasks = tasks.filter { it.id != taskId }
        saveTasks()
    }

    private fun saveTasks() {
        viewModelScope.launch {
            repository.saveTasks(tasks)
        }
    }


}