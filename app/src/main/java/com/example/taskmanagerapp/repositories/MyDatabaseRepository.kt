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

    suspend fun deleteCategory(categoryName: String){
        databaseDao.deleteCategory(categoryName)
    }

    suspend fun insertNewCategory(category: Category) {
        databaseDao.insertNewCategory(category)
    }

    suspend fun insertNewTask(task : Task){
        databaseDao.insertNewTask(task)
    }

    suspend fun getTasksCount(categoryName: String) : Int{
        return databaseDao.getTasksCount(categoryName)
    }

    fun checkIfCategoryExist(newCategoryName: String): Int {
        return databaseDao.checkIfCategoryExist(newCategoryName)
    }
}