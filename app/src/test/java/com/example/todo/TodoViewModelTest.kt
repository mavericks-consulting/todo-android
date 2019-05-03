package com.example.todo

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime


class TodoViewModelTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun shouldAddATodoWithGivenDescription() {
        val todoRepository = mockk<TodoRepository>()
        val todoViewModel = TodoViewModel(todoRepository)
        val todosObserver = mockk<Observer<List<Todo>>>()
        val todoSlot = slot<Todo>()
        every {
            todoRepository.add(capture(todoSlot))
        } just Runs
        val mockTodos = listOf(Todo(1, "item", LocalDateTime.now(), TodoStatus.OPEN))
        every { todoRepository.todos } returns mockTodos
        every {todosObserver.onChanged(any())} just Runs

        val description = "todo item"
        todoViewModel.addTodo(description)
        todoViewModel.todos.observeForever(todosObserver)

        val expectedTodo = todoSlot.captured
        verify {
            todoRepository.add(expectedTodo)
        }
        assertEquals(expectedTodo.status, TodoStatus.OPEN)

        verify { todoRepository.todos }
        verify { todosObserver.onChanged(mockTodos) }
    }
}