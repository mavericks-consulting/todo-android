package com.example.todo

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

import java.time.LocalDateTime
import java.util.*

class TodoRepositoryTest {

    lateinit var todoRepository: TodoRepository

    @Before
    fun setup() {
        todoRepository = TodoRepository()
    }

    @Test
    fun shouldAddATodo() {
        val description = "todo item"
        val status = TodoStatus.OPEN
        val id = System.currentTimeMillis()
        val todo = Todo(id, description, LocalDateTime.now(), status)

        todoRepository.add(todo)

        assertEquals(Collections.singletonList(todo), todoRepository.todos)
    }

    @Test
    fun shouldReturnTodosInReverseChronologicalOrder() {
        val todoCreatedToday = Todo(System.currentTimeMillis(), "todo1", LocalDateTime.now(), TodoStatus.OPEN)
        val todoCreatedYesterday = Todo(System.currentTimeMillis(), "todo2", LocalDateTime.now().minusDays(1), TodoStatus.OPEN)
        val todoCreatedTwoDaysAgo = Todo(System.currentTimeMillis(), "todo3", LocalDateTime.now().minusDays(2), TodoStatus.OPEN)

        val expectedTodos = listOf(todoCreatedToday, todoCreatedYesterday, todoCreatedTwoDaysAgo)

        todoRepository.add(todoCreatedTwoDaysAgo)
        todoRepository.add(todoCreatedYesterday)
        todoRepository.add(todoCreatedToday)

        assertEquals(expectedTodos, todoRepository.todos)
    }

    @Test
    fun shouldReturnOpenTodosBeforeCompletedTodos() {
        val openTodoCreatedYesterday = Todo(System.currentTimeMillis(), "todo1", LocalDateTime.now().minusDays(1), TodoStatus.OPEN)
        val completedTodoCreatedToday = Todo(System.currentTimeMillis(), "todo2", LocalDateTime.now(), TodoStatus.COMPLETED)
        val completedTodoCreatedYesterday = Todo(System.currentTimeMillis(), "todo3", LocalDateTime.now().minusDays(2), TodoStatus.COMPLETED)

        val expectedTodos = listOf(openTodoCreatedYesterday, completedTodoCreatedToday, completedTodoCreatedYesterday)

        todoRepository.add(completedTodoCreatedYesterday)
        todoRepository.add(openTodoCreatedYesterday)
        todoRepository.add(completedTodoCreatedToday)

        assertEquals(expectedTodos, todoRepository.todos)
    }

    @Test
    fun shouldUpdateTodo() {
        val todo = Todo(System.currentTimeMillis(), "todo1", LocalDateTime.now().minusDays(1), TodoStatus.OPEN)
        todoRepository.add(todo)
        val completedTodo = todo.copy(status = TodoStatus.COMPLETED)

        todoRepository.update(completedTodo)

        assertEquals(Collections.singletonList(completedTodo), todoRepository.todos)
    }

    @Test(expected = TodoNotFoundException::class)
    fun shouldThrowExceptionIfTodoDoesNotExist() {
        todoRepository.update(Todo(System.currentTimeMillis(), "todo1", LocalDateTime.now().minusDays(1), TodoStatus.OPEN))
    }
}
