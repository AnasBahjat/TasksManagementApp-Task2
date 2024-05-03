package com.example.taskmanagerapp.ui.common

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.DatabaseViewModel
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.databinding.CategoryCardViewBinding
import com.example.taskmanagerapp.models.Category

class CategoriesAdapter(private val categories : List<Category>,private val context : Context) :  RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>(){
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
            //binding.tasksCount.text = context.getString(R.string.tasks_count,category.tasksCount.toString())
            binding.categoryName.text=category.categoryName
            changeButtonBorderColor(category.categoryColor)
        }

        private fun changeButtonBorderColor(color: String) {
           binding.categoryColor.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        }
    }

}