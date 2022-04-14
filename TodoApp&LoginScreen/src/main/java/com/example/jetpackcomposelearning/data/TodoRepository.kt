package com.example.jetpackcomposelearning.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {
    suspend fun insertTodo(todo:Todo) = todoDao.insertTodo(todo)
    suspend fun delete(todo:Todo) = withContext(Dispatchers.IO){
        todoDao.deleteTodo(todo)
    }
    fun getAllTodos() = todoDao.getAllTodos()
}