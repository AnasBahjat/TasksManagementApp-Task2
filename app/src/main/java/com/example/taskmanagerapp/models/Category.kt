package com.example.taskmanagerapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(@PrimaryKey (autoGenerate = true) val categoryId : Int,
                     val categoryName : String,
                     val categoryColor : String)