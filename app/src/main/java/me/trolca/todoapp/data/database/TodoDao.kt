package me.trolca.todoapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoTask")
    fun getAll(): Flow<List<TodoTask>>

    @Insert
    suspend fun insertTodo(todoTask: TodoTask)

    @Update
    suspend fun updateChecked(todoTask: TodoTask)

    @Delete
    suspend fun deleteTodo(todoTask: TodoTask)
}