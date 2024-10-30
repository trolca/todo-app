package me.trolca.todoapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.trolca.todoapp.data.database.TodoTask
import kotlin.math.log

class TodoListView(private val todoRepo: TodoRepo) : ViewModel() {
    private val _uiState = MutableStateFlow(emptyList<TodoTask>())
    val uiState: StateFlow<List<TodoTask>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            todoRepo.allTasks.collect{ items ->
                _uiState.update {
                    items
                }
            }
        }

    }

    fun addTask(todoTask: TodoTask){
        viewModelScope.launch {
            todoRepo.addTask(todoTask)
            todoRepo.allTasks.collect{ items ->
                _uiState.update {
                    items
                }
            }
        }
    }

    fun removeTask(todoTask: TodoTask){
        viewModelScope.launch {
            todoRepo.removeTask(todoTask)

            todoRepo.allTasks.collect{ items ->
                _uiState.update {
                    items
                }
            }
        }


    }

    fun updateTask(todoTask: TodoTask, finished: Boolean){
        todoTask.finished = finished
        viewModelScope.launch {
            todoRepo.updateChecked(todoTask)
            todoRepo.allTasks.collect{ items ->
                _uiState.update {
                    items
                }
            }
        }
    }
}

class TodoListViewFactory(private val todoRepo: TodoRepo): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListView::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoListView(todoRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }

}