package com.example.stmpapp


import com.example.stmpapp.data.Task
import com.example.stmpapp.utils.TaskUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun task_adding() {
        val task = Task(description = "Task 1")
        assertEquals("Task 1", task.description)

    }

    @Test
    fun task_completion() {
        val task = Task(description = "Task 1")
        task.isCompleted = true
        assertEquals(true, task.isCompleted)
    }

    @Test
    fun task_selection() {
        var task = Task(description = "Task 1")
        task.isSelected = true
        assertEquals(true, task.isSelected)
    }

    @Test
    fun task_id() {
        var task = Task(description = "Task 1")
        assertEquals("Task 1", task.description)
    }

    @Test
    fun task_constructor_assigning_id() {
        val taskDescription = "Test task with random ID"
        val myTask = Task(description = taskDescription)
        val generatedId = myTask.id

        assertNotNull(generatedId)
        assertTrue(generatedId.isNotEmpty())
        assertEquals(36, generatedId.length)
        assertTrue(generatedId.contains("-"))

        println("Generated ID: $generatedId for task: '${myTask.description}'")
    }
}