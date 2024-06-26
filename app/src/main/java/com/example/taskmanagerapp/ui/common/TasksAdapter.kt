package com.example.taskmanagerapp.ui.common

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.databinding.TasksCardViewBinding
import com.example.taskmanagerapp.models.Task

class TasksAdapter(private val tasksList : List<Task>,private val context : Context) : RecyclerView.Adapter<TasksAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TasksCardViewBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksAdapter.MyViewHolder, position: Int) {
        val task = tasksList[position]
        return holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    inner class MyViewHolder(private val binding : TasksCardViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(task : Task){
            binding.taskName.text = task.taskName
            binding.taskDue.text=context.getString(R.string.dueString,task.taskDue)
            binding.taskPriorityButton.text = task.priority
            val border = GradientDrawable()
            border.setColor(Color.WHITE)
            val cornerRadius = context.resources.getDimensionPixelSize(R.dimen.radius_value).toFloat()
            border.cornerRadius = cornerRadius
            Log.d("the color is ${task.categoryColor}","the color is ${task.categoryColor}")
            border.setStroke(6,Color.parseColor(task.categoryColor))
            binding.taskPriorityButton.background = border

           if(task.categoryColor == "#FFFFFFFF"){
               binding.checkBox.buttonTintList = ColorStateList.valueOf(Color.BLACK)
           }
            else {
               binding.checkBox.buttonTintList = ColorStateList.valueOf(Color.parseColor(task.categoryColor))
           }
        }
    }
}