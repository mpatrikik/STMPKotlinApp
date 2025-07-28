import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stmpapp.data.TaskViewModel
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars, // ⬅️ Automatikus insets!
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // ⬅️ Ez adja hozzá a status/nav bar paddinget
                .padding(16.dp) // opcionális: belső padding dizájnhoz
        ) {
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

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
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
}
