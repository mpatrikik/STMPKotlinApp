package com.example.stmpapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stmpapp.data.TaskViewModel
import com.example.stmpapp.ui.components.FilterDropdown
import com.example.stmpapp.ui.components.TaskItem

@Preview
@Composable
fun TaskListScreenPreview() {
    val viewModel = TaskViewModel()
    TaskListScreen(viewModel)
}

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    var newTask by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("New Task") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Button(onClick = {
                viewModel.addTask(newTask)
                newTask = ""
            }) {
                Text("Add")
            }

            FilterDropdown(
                selectedFilter = filter,
                onFilterChange = { filter = it }
            )
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            val filteredTasks = viewModel.tasks.filter {
                when (filter) {
                    "Completed" -> it.isCompleted
                    "Incomplete" -> !it.isCompleted
                    else -> true
                }
            }

            items(filteredTasks.size) { index ->
                val task = filteredTasks[index]
                TaskItem(
                    task = task,
                    onToggleComplete = { viewModel.toggleCompleted(task.id) },
                    onToggleSelect = { viewModel.toggleSelection(task.id) }
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.markSelected(true) }) {
                Text("Mark Completed")
            }
            Button(onClick = { viewModel.markSelected(false) }) {
                Text("Mark Incomplete")
            }
            Button(onClick = { viewModel.deleteSelected() }) {
                Text("Delete")
            }
        }
    }
}
