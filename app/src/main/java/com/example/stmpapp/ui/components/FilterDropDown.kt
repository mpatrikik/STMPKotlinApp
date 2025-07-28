package com.example.stmpapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FilterDropdownPreview() {
    FilterDropdown(selectedFilter = "All", onFilterChange = {})
}

@Composable
fun FilterDropdown(selectedFilter: String, onFilterChange: (String) -> Unit) {
    val filters = listOf("All", "Completed", "Incomplete")
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedFilter)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            filters.forEach { filter ->
                DropdownMenuItem(
                    text = { Text(filter) },
                    onClick = {
                        onFilterChange(filter)
                        expanded = false
                    }
                )
            }
        }
    }
}
