package com.example.stmpapp

import com.example.stmpapp.ui.screens.TaskListScreen
import TaskRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.stmpapp.data.TaskViewModel
import com.example.stmpapp.ui.theme.STMPAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val repository = TaskRepository(this)
        val viewModel = TaskViewModel(repository)
        setContent {
            STMPAppTheme {
                TaskListScreen(viewModel)
            }
        }
    }
}