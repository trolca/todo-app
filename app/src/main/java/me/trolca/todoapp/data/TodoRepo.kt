package me.trolca.todoapp.data

import kotlinx.coroutines.flow.Flow
import me.trolca.todoapp.data.database.TodoDatabase
import me.trolca.todoapp.data.database.TodoTask

class TodoRepo(todoDatabase: TodoDatabase) {
    private val todoDao = todoDatabase.todoDao()

    val allTasks: Flow<List<TodoTask>> = todoDao.getAll()

    suspend fun addTask(todoTask: TodoTask){
        todoDao.insertTodo(todoTask)
    }

    suspend fun removeTask(todoTask: TodoTask){
        todoDao.deleteTodo(todoTask)
    }

    suspend fun updateChecked(todoTask: TodoTask){
        todoDao.updateChecked(todoTask)
    }

}