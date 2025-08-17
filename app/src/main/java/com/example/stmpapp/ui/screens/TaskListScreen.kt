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
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stmpapp.data.TaskViewModel // Assuming Task class has isCompleted
import com.example.stmpapp.ui.components.TaskItem
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

enum class TaskFilter {
    ALL,
    COMPLETED,
    INCOMPLETE
}

//@Preview
//@Composable
//fun TaskListScreenPreview() {
//    val viewModel = TaskViewModel()
//    TaskListScreen(viewModel)
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    var newTask by remember { mutableStateOf("") }
    var currentFilter by remember { mutableStateOf(TaskFilter.ALL) }
    var showEmptyTaskDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = newTask,
                onValueChange = { newTask = it },
                label = { Text("Enter task") },
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
                        } else { showEmptyTaskDialog = true }
                    },
                    modifier = Modifier.weight(1f).fillMaxHeight()
                ) {
                    Text("Add task")
                }

                Spacer(modifier = Modifier.width(8.dp))

                var expanded by remember { mutableStateOf(false) }
                val filterOptions = TaskFilter.values()

                Box(modifier = Modifier.wrapContentWidth()) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { if (!it) { expanded = false } }
                    ) {
                        Button(
                            onClick = { expanded = !expanded },
                            modifier = Modifier
                                .menuAnchor()
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
                        .padding(top = 8.dp) // Add some space above the list
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
                    onClick = { viewModel.deleteSelected() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Delete Selected")
                }
            }
        }
    }
}