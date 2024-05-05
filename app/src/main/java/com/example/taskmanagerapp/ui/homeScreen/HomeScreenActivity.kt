package com.example.taskmanagerapp.ui.homeScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.DatabaseViewModel
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.database.DatabaseHandler
import com.example.taskmanagerapp.databinding.ActivityHomeScreenBinding
import com.example.taskmanagerapp.models.Category
import com.example.taskmanagerapp.models.Task
import com.example.taskmanagerapp.ui.common.CategoriesAdapter
import com.example.taskmanagerapp.ui.common.MyNotificationManager
import com.example.taskmanagerapp.ui.common.RecyclerViewItemDirectionTasks
import com.example.taskmanagerapp.ui.common.RecyclerViewItemsDecoration
import com.example.taskmanagerapp.ui.common.TasksAdapter
import com.example.taskmanagerapp.ui.newTaskScreen.NewTaskActivity
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale


class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeScreenBinding
    private lateinit var categoriesAdapter : CategoriesAdapter
    private lateinit var tasksAdapter : TasksAdapter
    private lateinit var viewModel : DatabaseViewModel
    private lateinit var notification : MyNotificationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initBinding()
        setContentView(binding.root)
        notification = MyNotificationManager(this)
        notification.createNotification("TEST","anas bahjat")
        initialize()
    }

    private fun initialize(){
        initViewModel()
        initRecyclerView()
        setRecyclerViewsDecoration()
        binding.addNewTaskBtn.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity,NewTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]
    }

    private fun initBinding(){
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
    }

    private fun initRecyclerView(){
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.tasksRecyclerView.layoutManager=LinearLayoutManager(this)
        setCategoriesAdapter()
        setTasksAdapter()
    }

    private fun setCategoriesAdapter(){
        viewModel.getAllCategories().observe(this) { categoriesList ->
            if (categoriesList.isEmpty()) {
                binding.noCategoriesText.visibility = View.VISIBLE
                binding.categoriesRecyclerView.visibility = View.GONE
            } else {
                binding.noCategoriesText.visibility = View.GONE
                binding.categoriesRecyclerView.visibility = View.VISIBLE
                categoriesAdapter = CategoriesAdapter(categoriesList, this, viewModel)
                binding.categoriesRecyclerView.adapter = categoriesAdapter
            }
        }
    }

    private fun setTasksAdapter(){
        viewModel.getAllTasks().observe(this) { tasksList ->
            if(tasksList.isEmpty()){
                binding.noTasksText.visibility = View.VISIBLE
                binding.tasksRecyclerView.visibility = View.GONE
            }
            else {
                val sortedTasks = sortTasks(tasksList)
                binding.noTasksText.visibility = View.GONE
                binding.tasksRecyclerView.visibility = View.VISIBLE
                tasksAdapter = TasksAdapter(sortedTasks,this)
                binding.tasksRecyclerView.adapter=tasksAdapter
            }
        }
    }

    private fun sortTasks(tasksList : List<Task>) : List<Task>{
        val dateFormat  = SimpleDateFormat("MMM dd,yyyy, hh:mm a", Locale.US)
        val sortedTasks = tasksList.sortedWith(compareBy{
            dateFormat.parse(it.taskDue)})
        return sortedTasks
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