package com.example.taskmanagerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanagerapp.models.Category
import com.example.taskmanagerapp.models.Task

@Database(entities = [Category::class, Task::class],version = 1)
abstract class DatabaseHandler : RoomDatabase(){
    abstract fun databaseDao(): DatabaseDao

    companion object{
        private var databaseInstance : DatabaseHandler? = null

        fun getDatabase(context : Context) : DatabaseHandler{
            val tempInstance = databaseInstance
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    DatabaseHandler::class.java,
                    "task2DB").allowMainThreadQueries().build()
                databaseInstance = instance
                return instance
            }
        }
    }

}