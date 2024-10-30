package me.trolca.todoapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoTask(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "task_desc") val taskDesc: String,
    @ColumnInfo(name = "has_finished") var finished: Boolean
)