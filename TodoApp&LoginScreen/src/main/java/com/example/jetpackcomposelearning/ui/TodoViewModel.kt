package com.example.jetpackcomposelearning.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.jetpackcomposelearning.data.Todo
import com.example.jetpackcomposelearning.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
):ViewModel() {

    val response: MutableState<List<Todo>> = mutableStateOf(listOf())

    fun insertTodo(todo:Todo) = viewModelScope.launch {
        todoRepository.insertTodo(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        todoRepository.delete(todo)
    }

    init {
        getAllTodo()
    }

    private fun getAllTodo() = viewModelScope.launch {
        todoRepository.getAllTodos()
        .catch { e->
            Log.d("main","Exception ${e.message}")
        }.collect {
            response.value = it
        }
    }
}
