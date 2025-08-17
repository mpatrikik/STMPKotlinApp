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

    fun toggleSelection(taskId: String) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(isSelected = !it.isSelected)
            else it
        }
        saveTasks()
    }

    fun deleteSelected() {
        tasks = tasks.filter { !it.isSelected }
        saveTasks()
    }

    fun markSelected(completed: Boolean) {
        tasks = tasks.map {
            if (it.isSelected) it.copy(isCompleted = completed)
            else it
        }
        saveTasks()
    }

    private fun saveTasks() {
        viewModelScope.launch {
            repository.saveTasks(tasks)
        }
    }


}