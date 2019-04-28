package com.example.todo

import java.time.LocalDateTime

data class Todo(val id: Long, val description: String, val createdAt: LocalDateTime, val status: TodoStatus)
