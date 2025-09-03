package com.example.stmpapp

import com.example.stmpapp.ui.screens.TaskListScreen
import TaskRepository
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import com.example.stmpapp.ui.theme.MyBackgroundGradient
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.stmpapp.data.TaskViewModel
import com.example.stmpapp.ui.theme.STMPAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val repository = TaskRepository(this)
        val viewModel = TaskViewModel(repository)
        setContent {
            STMPAppTheme {
                val view = LocalView.current
                if (!view.isInEditMode) {
                    LaunchedEffect(Unit) {
                        val window = (view.context as Activity).window
                        val insetsController = WindowCompat.getInsetsController(window, view)
                        insetsController.hide(WindowInsetsCompat.Type.systemBars())
                        insetsController.systemBarsBehavior =
                            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = MyBackgroundGradient)
                ) {
                    TaskListScreen(viewModel)
                }
            }
        }
    }
}