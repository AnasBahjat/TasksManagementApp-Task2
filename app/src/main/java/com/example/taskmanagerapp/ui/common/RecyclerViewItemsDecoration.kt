package com.example.taskmanagerapp.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemsDecoration (private val spaceSize: Int): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            right = spaceSize
        }
    }
}