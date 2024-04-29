package com.example.taskmanagerapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(@PrimaryKey(autoGenerate = true) val taskId : Int,
    @ColumnInfo val taskName : String,
    @ColumnInfo val taskDue : String,
    @ColumnInfo val taskChecked : Boolean,
    @ColumnInfo val categoryColor : String,
    @ColumnInfo val priority : Int,
    @ColumnInfo val categoryName: String)