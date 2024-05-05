package com.example.taskmanagerapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.taskmanagerapp.database.DatabaseHandler
import com.example.taskmanagerapp.models.Category
import com.example.taskmanagerapp.models.Task
import androidx.lifecycle.viewModelScope
import com.example.taskmanagerapp.repositories.MyDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DatabaseViewModel(application : Application) : AndroidViewModel(application){
    private lateinit var allCategories : LiveData<List<Category>>
    private lateinit var allTasks : LiveData<List<Task>>
    private lateinit var repo : MyDatabaseRepository

    init{
        val databaseDao = DatabaseHandler.getDatabase(application).databaseDao()
        repo = MyDatabaseRepository(databaseDao)
        allCategories = repo.getSavedCategories()
        allTasks = repo.getSavedTasks()
    }


    fun getAllCategories() : LiveData<List<Category>>{
        return allCategories
    }

    fun getAllTasks() : LiveData<List<Task>> {
        return allTasks
    }

    fun insertCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertNewCategory(category)
        }
    }

    fun insertTask(task : Task){
        viewModelScope.launch {
            repo.insertNewTask(task)
        }
    }

    fun deleteCategory(categoryName: String){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteCategory(categoryName)
        }
    }

    fun deleteTask(taskId : Int){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteTask(taskId)
        }
    }


    fun getTasksCount(categoryName: String , callback : (Int) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val count = repo.getTasksCount(categoryName)
            withContext(Dispatchers.Main) {
                callback(count)
            }
        }
    }

    fun checkIfCategoryExist(newCategoryName: String, callback: (Int) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repo.checkIfCategoryExist(newCategoryName)
            withContext(Dispatchers.Main){
                callback(res)
            }

        }
    }
}