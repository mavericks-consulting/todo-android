package com.example.todo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView

class TodoAdapter(
    todos: List<Todo>,
    private val statusChangedListener: (Todo, Boolean) -> Unit
) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todos = todos
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var todoDescriptionView = view.findViewById<TextView>(R.id.description)
        var statusCheckbox = view.findViewById<CheckBox>(R.id.status)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoAdapter.TodoViewHolder {
        val todoItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false) as TextView

        return TodoViewHolder(todoItemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.todoDescriptionView.text = todo.description
        holder.statusCheckbox.isChecked = todo.status == TodoStatus.COMPLETED

        holder.statusCheckbox.setOnCheckedChangeListener { checkbox: CompoundButton, isChecked: Boolean ->
            statusChangedListener(todo, isChecked)
        }
    }

    override fun getItemCount() = todos.size
}


