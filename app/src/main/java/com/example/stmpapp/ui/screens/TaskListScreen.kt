package com.example.stmpapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stmpapp.data.TaskViewModel
import com.example.stmpapp.ui.components.TaskItem

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    var newTask by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(16.dp)) {
        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("Enter task") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.addTask(newTask)
                newTask = ""
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Task")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.tasks.size) { index ->
                val task = viewModel.tasks[index]
                TaskItem(
                    task = task,
                    onToggleComplete = { viewModel.toggleCompleted(task.id) },
                    onToggleSelect = { viewModel.toggleSelection(task.id) }
                )
            }
        }

        Button(
            onClick = { viewModel.deleteSelected() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Delete Selected")
        }
    }
}
