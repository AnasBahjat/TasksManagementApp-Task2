package com.example.taskmanagerapp.ui.homeScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.databinding.ActivityHomeScreenBinding
import com.example.taskmanagerapp.models.Category
import com.example.taskmanagerapp.models.Task
import com.example.taskmanagerapp.ui.common.CategoriesAdapter
import com.example.taskmanagerapp.ui.common.RecyclerViewItemDirectionTasks
import com.example.taskmanagerapp.ui.common.RecyclerViewItemsDecoration
import com.example.taskmanagerapp.ui.common.TasksAdapter
import com.example.taskmanagerapp.ui.newTaskScreen.NewTaskActivity


class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeScreenBinding
    private lateinit var categoriesAdapter : CategoriesAdapter
    private lateinit var tasksAdapter : TasksAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initBinding()
        setContentView(binding.root)
        initialize()
    }

    private fun initialize(){
        initRecyclerView()
        setRecyclerViewsDecoration()
        binding.categoriesRecyclerView.adapter=categoriesAdapter
        binding.tasksRecyclerView.adapter = tasksAdapter
        binding.addNewTaskBtn.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity,NewTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initBinding(){
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
    }

    private fun initRecyclerView(){
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.tasksRecyclerView.layoutManager=LinearLayoutManager(this)


        val categoriesList : List<Category> = listOf(
            Category(0,"first task",false,
                "High","#0000000"), Category(0,"second task",false,
                "Low","#0000000"),Category(0,"second task",false,
                "Medium","#0000000"),Category(0,"second task",false,
                "High","#0000000"))


        val tasksList : List<Task> = listOf(Task(1,"First Task", "25 / 5 / 2024",false,"#00000000",1,"medium"),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2,"Medium",),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2,"Medium"),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2,"Medium"),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2,"Medium"),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2,"Medium"),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2,"Medium"),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2,"Medium"),
            Task(2,"Last Task", "258 / 4 / 2028",false,"#FFFFFFFF",2,"Medium"))
        tasksAdapter = TasksAdapter(tasksList,this)
        categoriesAdapter= CategoriesAdapter(categoriesList,this)
    }

    private fun setRecyclerViewsDecoration(){
        binding.categoriesRecyclerView.addItemDecoration(
            RecyclerViewItemsDecoration(resources.getDimensionPixelSize(R.dimen.spaces))
        )

        binding.tasksRecyclerView.addItemDecoration(
            RecyclerViewItemDirectionTasks(resources.getDimensionPixelSize(R.dimen.spacesTasks))
        )
    }
}