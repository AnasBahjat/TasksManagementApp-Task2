package com.example.taskmanagerapp.ui.common

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.DatabaseViewModel
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.databinding.CategoryCardViewBinding
import com.example.taskmanagerapp.models.Category

class CategoriesAdapter(private val categories : List<Category>,private val context : Context,private val viewModel: DatabaseViewModel) :  RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryCardViewBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
    inner class MyViewHolder(private val binding : CategoryCardViewBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            binding.categoryName.text=category.categoryName
            Log.d("category color is ${category.categoryColor}","${category.categoryName} ---> category color is ${category.categoryColor}")
            changeButtonBorderColor(category.categoryColor)
            viewModel.getTasksCount(category.categoryName) { count ->
                 if(count == 0 || count == 1)
                     binding.tasksCount.text = context.getString(R.string.tasks_count_text_odd,count.toString())
                else
                     binding.tasksCount.text = context.getString(R.string.tasks_count_text,count.toString())
        }
    }

        private fun changeButtonBorderColor(color: String) {
            binding.categoryColor.background = ColorDrawable(Color.parseColor(color))
        }
    }

}