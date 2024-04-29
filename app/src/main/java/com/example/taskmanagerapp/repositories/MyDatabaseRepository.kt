package com.example.taskmanagerapp.repositories

import androidx.lifecycle.LiveData
import com.example.taskmanagerapp.database.DatabaseDao
import com.example.taskmanagerapp.models.Category
import com.example.taskmanagerapp.models.Task

class MyDatabaseRepository(private val databaseDao : DatabaseDao) {
    fun getSavedCategories() : LiveData<List<Category>>{
        return databaseDao.getCurrentCategories()
    }

    fun getSavedTasks() : LiveData<List<Task>>{
        return databaseDao.getCurrentTasks()
    }

    suspend fun deleteTask(taskId : Int) {
        databaseDao.deleteTask(taskId)
    }

    suspend fun deleteCategory(categoryID : Int){
        databaseDao.deleteCategory(categoryID)
    }

    suspend fun insertNewCategory(category: Category) {
        databaseDao.insertNewCategory(category)
    }

    suspend fun insertNewTask(task : Task){
        databaseDao.insertNewTask(task)
    }
}