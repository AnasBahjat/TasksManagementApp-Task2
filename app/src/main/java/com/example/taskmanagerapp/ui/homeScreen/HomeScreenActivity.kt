package com.example.taskmanagerapp.ui.homeScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.databinding.ActivityMainBinding
import com.example.taskmanagerapp.models.Category
import com.example.taskmanagerapp.models.Task
import com.example.taskmanagerapp.ui.common.CategoriesAdapter
import com.example.taskmanagerapp.ui.common.RecyclerViewItemDirectionTasks
import com.example.taskmanagerapp.ui.common.RecyclerViewItemsDecoration
import com.example.taskmanagerapp.ui.common.TasksAdapter


class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var categoriesAdapter : CategoriesAdapter
    private lateinit var tasksAdapter : TasksAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater,)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize(){
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.tasksRecyclerView.layoutManager=LinearLayoutManager(this)
        val categoriesList : List<Category> = listOf(
            Category(0,"first task","Category 1",false,
            1,"#0000000",10), Category(0,"second task","Category 2",false,
                1,"#0000000",10),Category(0,"second task","Category 2",false,
                1,"#0000000",10),Category(0,"second task","Category 2",false,
                1,"#0000000",10))


        val tasksList : List<Task> = listOf(Task(1,"First Task", "25 / 5 / 2024",false,"#00000000",1),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2))
        tasksAdapter = TasksAdapter(tasksList,this)
        categoriesAdapter= CategoriesAdapter(categoriesList,this)
        binding.categoriesRecyclerView.addItemDecoration(
            RecyclerViewItemsDecoration(resources.getDimensionPixelSize(R.dimen.spaces))
        )

        binding.tasksRecyclerView.addItemDecoration(
            RecyclerViewItemDirectionTasks(resources.getDimensionPixelSize(R.dimen.spacesTasks))
        )
        binding.categoriesRecyclerView.adapter=categoriesAdapter
        binding.tasksRecyclerView.adapter = tasksAdapter
    }


}