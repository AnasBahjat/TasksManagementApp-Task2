package com.example.taskmanagerapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(@PrimaryKey(autoGenerate = true) val categoryId : Int,
                    @ColumnInfo val taskName : String,
                    @ColumnInfo val categoryName : String,
                    @ColumnInfo val isCompleted : Boolean,
                    @ColumnInfo val priority : Int,
                    @ColumnInfo val categoryColor : String,
                    @ColumnInfo(defaultValue = "0") val tasksCount : Int)