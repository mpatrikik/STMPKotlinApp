package com.example.stmpapp.data

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TaskRepository(application)

    var tasks by mutableStateOf(listOf<Task>())
    private set

    init {
        loadTasks()
    }

    private fun loadTasks() {
        tasks = repository.loadTasks()
    }

    private fun persist() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveTasks(tasks)
        }
    }

    fun addTask(description: String) {
        tasks = tasks + Task(description = description)
        persist()
    }

    fun toggleCompleted(taskId: String) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(isCompleted = !it.isCompleted)
             else it
        }
        persist()
    }

    fun toggleSelection(taskId: String) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(isSelected = !it.isSelected)
            else it
        }
        persist()
    }

    fun deleteSelected() {
        tasks = tasks.filter { !it.isSelected }
        persist()
    }

    fun markSelected(completed: Boolean) {
        tasks = tasks.map {
            if (it.isSelected) it.copy(isCompleted = completed)
            else it
        }
        persist()
    }
}