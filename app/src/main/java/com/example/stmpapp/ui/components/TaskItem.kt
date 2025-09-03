package com.example.stmpapp.ui.components

import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.example.stmpapp.data.Task


@Preview
@Composable
fun TaskItemPreview() {
    val task = Task(description = "Task 1")
    TaskItem(task = task, onToggleComplete = {}, onDelete = {})
}

@Composable
fun TaskItem(
    task: Task,
    onToggleComplete: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            // Display task description, differentiating between completed and pending
            text = task.description + if (task.isCompleted) " ✓" else " ❓",
            modifier = Modifier
                .weight(1f) // Allow text to take available space
                .padding(start = 8.dp, end = 8.dp) // Add some padding
        )
        Row { // Row for buttons
            Button(onClick = onToggleComplete) {
                Text(if (task.isCompleted) "Undo" else "Complete")
            }
            Spacer(modifier = Modifier.width(8.dp)) // Spacer between buttons
            IconButton(onClick = onDelete) { // Delete button
                Icon(Icons.Filled.Delete, contentDescription = "Delete Task")
            }
        }
    }
}
