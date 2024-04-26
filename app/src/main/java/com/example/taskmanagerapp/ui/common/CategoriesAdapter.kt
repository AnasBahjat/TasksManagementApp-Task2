package com.example.taskmanagerapp.ui.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            val tasksCount : Int = category.tasksCount
            binding.tasksCount.text = context.getString(R.string.tasks_count,tasksCount.toString())
            binding.categoryName.text=category.categoryName
            Log.d("","cat name ----> ${category.categoryName}")
            Log.d("Cat color -----> ${category.categoryColor}","Cat color -----> ${category.categoryColor}")
           // changeButtonBorderColor(category.categoryColor)
        }

        private fun changeButtonBorderColor(color: String) {
            val drawable = binding.categoryColor.background as GradientDrawable
            drawable.setStroke(10,Color.parseColor(color))
            binding.categoryColor.background = drawable
        }
    }

}