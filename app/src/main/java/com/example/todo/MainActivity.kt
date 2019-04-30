package com.example.todo

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewManager: LinearLayoutManager

    private lateinit var viewAdapter: TodoAdapter

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = TodoAdapter(Collections.emptyList<Todo>()) { todo: Todo, isChecked: Boolean ->
            statusChangedFor(todo, isChecked)
        }

        recyclerView = findViewById<RecyclerView>(R.id.todosRecyclerView).apply {

            layoutManager = viewManager

            adapter = viewAdapter

        }
    }

    private fun statusChangedFor(todo: Todo, checked: Boolean) {
        // view model should update the todo with new status
    }

    fun addTodo(view: View) {
        val newTodoDescription = findViewById<TextInputEditText>(R.id.newTodoDescription).text
        // add the new todo to the repo via view model
    }


}
