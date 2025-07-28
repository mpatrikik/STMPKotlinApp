package com.example.stmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.stmpapp.data.TaskViewModel
import com.example.stmpapp.ui.screens.TaskListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = TaskViewModel()
        setContent {
            TaskListScreen(viewModel)
        }
    }
}