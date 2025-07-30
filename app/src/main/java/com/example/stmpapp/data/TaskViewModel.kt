package com.example.stmpapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.filter

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = AddDatabase.getDatabase(application).taskDao()

    var tasks: StateFlow<List<TaskUiModel>> = taskDao.getAllTasks()
        .map { tasks -> tasks.map { TaskUiModel.fromTask(it) } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(description: String) {
        viewModelScope.launch {
            if (description.isNotBlank()) {
                val newTask = Task(description = description)
                taskDao.insertTask(newTask)
            }
        }
    }

    fun toggleCompleted(taskId: String) {
        viewModelScope.launch {
            val task = taskDao.getTaskById(taskId)
            task?.let {
                val updatedTask = it.copy(isCompleted = !it.isCompleted)
                taskDao.updateTask(updatedTask)
            }
        }
    }

    fun toggleSelection(taskId: String) {
        viewModelScope.launch {
            val task = taskDao.getTaskById(taskId)
            task?.let {
                val updatedTask = it.copy(isSelected = !it.isSelected)
                taskDao.updateTask(updatedTask)
            }
    }
    }

    fun deleteSelected() {
        viewModelScope.launch {
            val allTasks = tasks.value
            val selectedTaskIds = allTasks.filter { it.isSelected }.map { it.id }
            if (selectedTaskIds.isNotEmpty()) {
                taskDao.deleteTasksByIds(selectedTaskIds)
            }
        }
    }
}

data class TaskUiModel(
    val id: String,
    val description: String,
    val isCompleted: Boolean,
    val isSelected: Boolean
) {
    companion object {
        fun fromTask(task: Task): TaskUiModel {
            return TaskUiModel(
                id = task.id,
                description = task.description,
                isCompleted = task.isCompleted,
                isSelected = task.isSelected
            )
        }

    }
}