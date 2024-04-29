package com.example.taskmanagerapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskmanagerapp.models.Category
import com.example.taskmanagerapp.models.Task

@Dao
interface DatabaseDao {
    @Insert
    suspend fun insertNewCategory(category : Category)

    @Query("SELECT * from Category")
    fun getCurrentCategories() : LiveData<List<Category>>

    @Query("DELETE FROM Category where categoryId = :id")
    suspend fun deleteCategory(id : Int)

    @Insert
    suspend fun insertNewTask(task : Task)

    @Query("DELETE FROM Task where taskId=:id")
    suspend fun deleteTask(id : Int)

    @Query("SELECT * FROM Task")
    fun getCurrentTasks() : LiveData<List<Task>>
}