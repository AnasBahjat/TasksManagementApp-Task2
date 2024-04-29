package com.example.taskmanagerapp.ui.utils

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent

class CustomDatePicker(
    context: Context,
    listener: DatePickerDialog.OnDateSetListener,
    year: Int,
    month: Int,
    dayOfMonth: Int
) : DatePickerDialog(context, listener, year, month, dayOfMonth) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCanceledOnTouchOutside(false)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (isOutOfBounds(ev)) {
            return true
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun isOutOfBounds(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        val dialogBounds = intArrayOf(0, 0)
        window?.decorView?.getLocationOnScreen(dialogBounds)
        return (x < dialogBounds[0] || x >= dialogBounds[0] + window?.decorView?.width!!
                || y < dialogBounds[1] || y >= dialogBounds[1] + window?.decorView?.height!!)
    }
}