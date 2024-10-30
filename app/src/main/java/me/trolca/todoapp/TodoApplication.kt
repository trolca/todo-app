package me.trolca.todoapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import me.trolca.todoapp.data.TodoRepo
import me.trolca.todoapp.data.database.TodoDatabase

class TodoApplication(private val context: Context) : Application() {

    val database: TodoDatabase by lazy{ Room.databaseBuilder(context,
                TodoDatabase::class.java,
                "todo_database"
            ).fallbackToDestructiveMigration().build() }

    val todoRepo: TodoRepo by lazy { TodoRepo(database) }

}