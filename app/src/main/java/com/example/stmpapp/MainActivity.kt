package com.example.stmpapp

import TaskListScreen
import TaskRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.stmpapp.data.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val repository = TaskRepository(this)
        val viewModel = TaskViewModel(repository)
        setContent {
            TaskListScreen(viewModel)
        }
    }
}