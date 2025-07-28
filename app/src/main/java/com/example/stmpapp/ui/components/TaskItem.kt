package com.example.stmpapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stmpapp.data.Task

@Composable
fun TaskItem(
    task: Task,
    onToggleComplete: () -> Unit,
    onToggleSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Checkbox(
                checked = task.selected,
                onCheckedChange = { onToggleSelect() }
            )
            Text(
                text = task.description + if (task.completed) " ✓" else " ❓",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        IconButton(onClick = onToggleComplete) {
            Icon(Icons.Default.Check, contentDescription = "Mark complete")
        }
    }
}
