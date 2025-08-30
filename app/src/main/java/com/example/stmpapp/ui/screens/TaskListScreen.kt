package com.example.stmpapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import com.example.stmpapp.data.TaskViewModel
import com.example.stmpapp.ui.components.TaskItem
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color // Ensure Color is imported
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.sp
import com.example.stmpapp.ui.theme.MyBackgroundGradient

// Removed: import com.example.stmpapp.ui.theme.MyBackgroundGradient // Not needed here anymore

enum class TaskFilter {
    ALL,
    COMPLETED,
    INCOMPLETE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var newTask by remember { mutableStateOf("") }
    var currentFilter by remember { mutableStateOf(TaskFilter.ALL) }
    var showEmptyTaskDialog by remember { mutableStateOf(false) }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize().background(brush = MyBackgroundGradient))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        )
        {
            OutlinedTextField(
                value = newTask,
                onValueChange = {
                    val filteredText = it.filter { char ->
                        char.isLetterOrDigit() && char in 'a'..'z' || char in 'A'..'Z' || char in '0'..'9' ||
                                "áéíóöőúüűÁÉÍÓÖŐÚÜŰ".contains(char)
                    }
                    newTask = if (filteredText.length <= 20) filteredText else filteredText.substring(0, 20)
                },
                label = { Text("Enter task (max 20 Hungarian letters/numbers)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (newTask.isNotBlank()) {
                            viewModel.addTask(newTask)
                            newTask = ""
                            keyboardController?.hide()
                        } else { showEmptyTaskDialog = true }
                    },
                    modifier = Modifier.weight(1f).fillMaxHeight()
                ) {
                    Text("Add task")
                }

                Spacer(modifier = Modifier.width(8.dp))

                var expanded by remember { mutableStateOf(false) }
                val filterOptions = TaskFilter.entries.toTypedArray()

                Box(modifier = Modifier.wrapContentWidth()) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { if (!it) { expanded = false } }
                    ) {
                        Button(
                            onClick = { expanded = !expanded },
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxHeight(),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(currentFilter.name.replaceFirstChar { it.uppercase() })
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        }

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.width(IntrinsicSize.Max)
                        ) {
                            filterOptions.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption.name.replaceFirstChar { it.uppercase() }) },
                                    onClick = {
                                        currentFilter = selectionOption
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            if (showEmptyTaskDialog) {
                AlertDialog(
                    onDismissRequest = { showEmptyTaskDialog = false },
                    title = { Text("Empty Task") },
                    text = { Text("Task description cannot be empty.") },
                    confirmButton = {
                        Button(
                            onClick = { showEmptyTaskDialog = false }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }

            if (showDeleteConfirmationDialog) {
                AlertDialog(
                    onDismissRequest = { showDeleteConfirmationDialog = false },
                    title = { Text("Confirm Deletion") },
                    text = { Text("Are you sure you want to delete the selected task(s)?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.deleteSelected()
                                showDeleteConfirmationDialog = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDeleteConfirmationDialog = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }

            val filteredTasks = remember(viewModel.tasks, currentFilter) {
                when (currentFilter) {
                    TaskFilter.ALL -> viewModel.tasks.toList()
                    TaskFilter.COMPLETED -> viewModel.tasks.filter { it.isCompleted }.toList()
                    TaskFilter.INCOMPLETE -> viewModel.tasks.filter { !it.isCompleted }.toList()
                }
            }

            if (filteredTasks.isEmpty()) {
                Text(
                    text = when (currentFilter) {
                        TaskFilter.ALL -> "No tasks yet. Add one!"
                        TaskFilter.COMPLETED -> "No completed tasks."
                        TaskFilter.INCOMPLETE -> "No incomplete tasks."
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 8.dp)
                ) {
                    items(filteredTasks.size) { index ->
                        val task = filteredTasks[index]
                        TaskItem(
                            task = task,
                            onToggleComplete = { viewModel.toggleCompleted(task.id) },
                            onToggleSelect = { viewModel.toggleSelection(task.id) }
                        )
                    }
                }
            }


            if (viewModel.tasks.any { it.isSelected }) {
                Button(
                    onClick = { showDeleteConfirmationDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .size(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                )
                {
                    Text(
                        text = "Delete selected",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
