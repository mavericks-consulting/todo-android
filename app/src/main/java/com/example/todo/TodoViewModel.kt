package com.example.todo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import java.time.LocalDateTime

class TodoViewModel(val todoRepository: TodoRepository): ViewModel() {
    val todos: MutableLiveData<List<Todo>> = MutableLiveData()

    init {
        todos.value = todoRepository.todos
    }
    fun addTodo(description: String) {
        val todo = Todo(System.currentTimeMillis(), description, LocalDateTime.now(), TodoStatus.OPEN)
        todoRepository.add(todo)

        todos.value = todoRepository.todos
    }

}
