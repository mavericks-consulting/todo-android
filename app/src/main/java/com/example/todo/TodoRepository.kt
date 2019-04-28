package com.example.todo

import java.util.*

class TodoRepository {
    var todos: List<Todo> = Collections.emptyList()
        get() = field.sortedWith(compareBy<Todo> { it.status }.thenByDescending { it.createdAt })
        private set

    fun add(todo: Todo) {
        todos = todos.plus(todo)
    }

    fun update(todo: Todo) {
        val todoExists = todos.any { it.id == todo.id }
        if(!todoExists) throw TodoNotFoundException()

        todos = todos.map { if (it.id == todo.id) todo else it }
    }
}
