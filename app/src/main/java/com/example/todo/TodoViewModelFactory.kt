package com.example.todo

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class TodoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(TodoRepository()) as T
    }

}
