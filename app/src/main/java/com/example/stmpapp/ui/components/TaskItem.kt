package com.example.stmpapp.ui.components

import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stmpapp.data.Task


@Preview
@Composable
fun TaskItemPreview() {
    val task = Task(description = "Task 1")
    TaskItem(task = task, onToggleComplete = {}, onToggleSelect = {})
}

@Composable
fun TaskItem(
    task: Task,
    onToggleComplete: () -> Unit,
    onToggleSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Checkbox(
                checked = task.isSelected,
                onCheckedChange = { onToggleSelect() }
            )
            Text(
                text = task.description + if (task.isCompleted) " ✓" else " ❓",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Button(onClick = onToggleComplete) {
            Text(if (task.isCompleted) "Undo" else "Complete")
        }
    }
}
