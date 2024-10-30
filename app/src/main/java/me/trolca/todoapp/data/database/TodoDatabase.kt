package me.trolca.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoTask::class], version = 2)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}