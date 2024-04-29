package com.example.taskmanagerapp.ui.newTaskScreen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.GridLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanagerapp.DatabaseViewModel
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.databinding.ActivityNewTaskBinding
import com.example.taskmanagerapp.databinding.ColorPickerLayoutBinding
import com.example.taskmanagerapp.databinding.PopupNewCategoryWindowLayoutBinding
import com.example.taskmanagerapp.databinding.TimePickerLayoutBinding
import com.example.taskmanagerapp.models.Category
import com.example.taskmanagerapp.ui.utils.Constants
import com.example.taskmanagerapp.ui.utils.CustomDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class NewTaskActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewTaskBinding
    private lateinit var popupNewCategoryWindowBinding : PopupNewCategoryWindowLayoutBinding
    private lateinit var colorPickerPopUpBinding : ColorPickerLayoutBinding
    private lateinit var popUpWindowNewCategory: PopupWindow
    private lateinit var popupWindowTimePickerBinding : TimePickerLayoutBinding
    private var newCategoryName : String = ""
    private var newCategoryColor : String = "#00000000"
    private lateinit var viewModel : DatabaseViewModel
    private val calendar = Calendar.getInstance()
    private var selectedColor : Int = 0
    private var rowCount : Int = 1
    private var columnCount : Int = 1
    private var priority = ""

    private var savedTime = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindingInit()
        setContentView(binding.root)
        init()
    }

    private fun bindingInit(){
        binding=ActivityNewTaskBinding.inflate(layoutInflater)
        popupNewCategoryWindowBinding = PopupNewCategoryWindowLayoutBinding.inflate(layoutInflater)
        colorPickerPopUpBinding = ColorPickerLayoutBinding.inflate(layoutInflater)
        popupWindowTimePickerBinding = TimePickerLayoutBinding.inflate(layoutInflater)
    }

    private fun init() {
        initViewModel()
        binding.addNewCategoryBtn.setOnClickListener{
            showNewCategoryPopupWindow()
        }

        binding.backImageBtn.setOnClickListener{
            finish()
        }

        binding.datePickerBtn.setOnClickListener {
            showDatePicker()
        }

        binding.datePickerImage.setOnClickListener {
            showDatePicker()
        }
        setPriorityButtons()
        setLowPriorityButton()
        setMidPriorityButton()
        setHighPriorityButton()
        checkCategoryNameText()
        addCategoryButtonClicked()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]
    }

    private fun addCategoryButtonClicked() {
        popupNewCategoryWindowBinding.addCategoryBtn.setOnClickListener {
            if(popupNewCategoryWindowBinding.categoryNamePopup.editText?.text.toString().isEmpty()){
                popupNewCategoryWindowBinding.categoryNamePopup.editText?.error = "*Please Set the category name "
            }
            else {
                newCategoryName = popupNewCategoryWindowBinding.categoryNamePopup.editText?.text.toString()
                Log.d("------> $newCategoryName  $newCategoryColor","----------> $newCategoryName  $newCategoryColor")
                popUpWindowNewCategory.dismiss()
                addNewCategoryButtonToGridLayout()
                val category = Category(0,newCategoryName,binding.completedCheckBox.isChecked,priority,newCategoryColor)
                viewModel.insertCategory(category)
                Toast.makeText(this,"New Category added ..",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkCategoryNameText() {
        popupNewCategoryWindowBinding.categoryNamePopup.editText?.doOnTextChanged { text, start, before, count ->
            popupNewCategoryWindowBinding.categoryNamePopup.editText?.error = null
        }
    }

    private fun setHighPriorityButton() {
        binding.highPriorityBtn.setOnClickListener{
            resetButtons()
            binding.highPriorityBtn.setBackgroundColor(getColor(R.color.light_blue))
            binding.highPriorityBtn.setTextColor(getColor(R.color.text_light_blue))
            priority="Low"
        }
    }

    private fun setMidPriorityButton() {
        binding.midPriorityBtn.setOnClickListener{
            resetButtons()
            binding.midPriorityBtn.setBackgroundColor(getColor(R.color.light_blue))
            binding.midPriorityBtn.setTextColor(getColor(R.color.text_light_blue))
            priority="Medium"
        }
    }

    private fun setLowPriorityButton() {
        binding.lowPriorityBtn.setOnClickListener{
            resetButtons()
            binding.lowPriorityBtn.setTextColor(getColor(R.color.text_light_blue))
            binding.lowPriorityBtn.setBackgroundColor(getColor(R.color.light_blue))
            priority="High"
        }
    }



    private fun setPriorityButtons() {
        binding.lowPriorityBtn.setBackgroundColor(getColor(R.color.button_normal))
        binding.midPriorityBtn.setBackgroundColor(getColor(R.color.button_normal))
        binding.highPriorityBtn.setBackgroundColor(getColor(R.color.button_normal))
    }

    private fun resetButtons(){
        binding.lowPriorityBtn.setBackgroundColor(getColor(R.color.button_normal))
        binding.midPriorityBtn.setBackgroundColor(getColor(R.color.button_normal))
        binding.highPriorityBtn.setBackgroundColor(getColor(R.color.button_normal))
        binding.lowPriorityBtn.setTextColor(Color.BLACK)
        binding.midPriorityBtn.setTextColor(Color.BLACK)
        binding.highPriorityBtn.setTextColor(Color.BLACK)
    }



    private fun showNewCategoryPopupWindow(){

        val width = WindowManager.LayoutParams.WRAP_CONTENT
        val height = WindowManager.LayoutParams.WRAP_CONTENT

        popUpWindowNewCategory = PopupWindow(popupNewCategoryWindowBinding.root,width,height,true)

        popUpWindowNewCategory.showAtLocation(popupNewCategoryWindowBinding.mainLayout, Gravity.CENTER,0,0)

        popupNewCategoryWindowBinding.addColorButton.setOnClickListener {
            showColorPicker()
        }

        popupNewCategoryWindowBinding.closeNewCategoryPopupWindow.setOnClickListener {
            popUpWindowNewCategory.dismiss()
        }
    }

    private fun showColorPicker(){
        val width = 1200
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        val colorPickerPopUpWindow = PopupWindow(colorPickerPopUpBinding.root,width,height,true)
        colorPickerPopUpWindow.showAtLocation(colorPickerPopUpBinding.mainLayoutColorPicker,Gravity.CENTER,0,0)

        colorPickerPopUpBinding.colorPickerView.setColorListener(object : ColorEnvelopeListener{
            override fun onColorSelected(envelope: ColorEnvelope, fromUser: Boolean) {
                    selectedColor = Color.parseColor("#${envelope.hexCode}")
                    newCategoryColor = "#${envelope.hexCode}"
                    colorPickerPopUpBinding.colorPickerSelectedColorPreView.setBackgroundColor(selectedColor)
            }
        })
        colorPickerPopUpBinding.colorPickerView.attachBrightnessSlider(colorPickerPopUpBinding.brightnessSlide)
        colorPickerPopUpBinding.colorPickerView.attachAlphaSlider(colorPickerPopUpBinding.alphaSlideBar)
        colorPickerPopUpBinding.chooseColorBtn.setOnClickListener {
            popupNewCategoryWindowBinding.newCategoryColor.setBackgroundColor(selectedColor)
            colorPickerPopUpWindow.dismiss()
        }
        colorPickerPopUpBinding.closeChooseColorPopupWindow.setOnClickListener {
            colorPickerPopUpWindow.dismiss()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = CustomDatePicker(this,
            { _ , year : Int , monthOfYear : Int , day : Int ->

                val selectedDate = Calendar.getInstance()
                selectedDate.set(year,monthOfYear,day)
                val dateFormat = SimpleDateFormat(Constants.DATE_FORAMT,Locale.ENGLISH)
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.dueDateText.text=formattedDate
                if(checkIfDateIsValid(year,monthOfYear,day) == 1){
                    binding.datePickerBtn.background=ContextCompat.getDrawable(this,R.drawable.black_curvey_layout)
                    showTimePicker(1)
                }
                else if(checkIfDateIsValid(year,monthOfYear,day) == 0){
                    showTimePicker(0)
                }
                else {
                    showErrorDialog(this.getString(R.string.error_date_title),this.getString(R.string.error_date_message))
                    binding.datePickerBtn.background=ContextCompat.getDrawable(this,R.drawable.red_curvey_layout)
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }

    private fun checkIfDateIsValid(year : Int ,month : Int , dayOfMonth : Int) : Int{
        Log.d("Month selected ----> $month ,, current month ----> ${LocalDate.now().monthValue}","Month selected ----> $month ,, current month ----> ${LocalDate.now().monthValue}")
        var flag = -1

        if(year == LocalDate.now().year && month + 1 == (LocalDate.now().monthValue) && dayOfMonth == LocalDate.now().dayOfMonth){
            return 0
        }

        if(year > LocalDate.now().year){
            flag = 1
        }
        else if (year < LocalDate.now().year){
            flag = -1
        }
        else {
            if(month + 1 > (LocalDate.now().monthValue)){
                flag = 1
            }
            else if(month + 1 < LocalDate.now().monthValue){
                flag = -1
            }
            else {
                if(dayOfMonth > LocalDate.now().dayOfMonth){
                    flag = 1
                }
                else if(dayOfMonth < LocalDate.now().dayOfMonth){
                    flag = -1
                }
            }
        }
        return flag
    }

    private fun showTimePicker(flag : Int) {
        val width = WindowManager.LayoutParams.WRAP_CONTENT
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        val popupWindowTimePicker = PopupWindow(popupWindowTimePickerBinding.root,width,height,true)
        popupWindowTimePicker.isOutsideTouchable=false
        popupWindowTimePicker.showAtLocation(popupWindowTimePickerBinding.mainLayoutTimePicker,Gravity.CENTER,0,0)
        val currentTime = Calendar.getInstance()

        popupWindowTimePickerBinding.timePicker.setOnTimeChangedListener{_,hours : Int, minutes : Int ->
            val selectedTime = Calendar.getInstance()
            selectedTime.set(Calendar.HOUR_OF_DAY,hours)
            selectedTime.set(Calendar.MINUTE,minutes)

            if(flag==0){
                if(selectedTime.timeInMillis < currentTime.timeInMillis){
                    showErrorDialog(getString(R.string.error_time_title),getString(R.string.error_time_message))
                }
            }
            else {
                var selectedHour = hours
                var amPm = ""

                when {hours == 0 -> { selectedHour += 12
                    amPm = "AM"
                }
                    hours == 12 -> amPm = "PM"
                    hours > 12 -> { selectedHour -= 12
                        amPm = "PM"
                    }
                    else -> amPm = "AM"
                }
                val hrs = if (hours < 10) "0$hours" else hours
                val min = if (minutes < 10) "0$minutes" else minutes
                val msg = "Time is: $hrs : $min $amPm"
                savedTime="$hrs:$min $amPm"
                popupWindowTimePickerBinding.timeText.text = msg
            }
        }

        popupWindowTimePickerBinding.selectTimeOkBtn.setOnClickListener{
            binding.dueDateText.text=this.getString(R.string.chosed_time,binding.dueDateText.text,savedTime)
            popupWindowTimePicker.dismiss()
        }
    }

    private fun showErrorDialog(title : String,message : String){
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setIcon(ContextCompat.getDrawable(this,R.drawable.error_icon_2))
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

   private fun addNewCategoryButtonToGridLayout(){
       val button = Button(this).apply {
           text = newCategoryName
           isAllCaps=false
       }
       if(rowCount == 1){
           val layoutParams = GridLayout.LayoutParams(
               GridLayout.spec(rowCount, GridLayout.FILL, 1f),
               GridLayout.spec(columnCount, GridLayout.FILL, 1f),
           )
           binding.gridLayout.addView(button,layoutParams)
           if(columnCount == 3)
               rowCount++
           columnCount++
       }
       else {
           val layoutParams = GridLayout.LayoutParams(
               GridLayout.spec(rowCount, GridLayout.FILL, 1f),
               GridLayout.spec(columnCount, GridLayout.FILL, 1f),
           ).apply {
               width = GridLayout.LayoutParams.WRAP_CONTENT
               height = GridLayout.LayoutParams.WRAP_CONTENT
               setGravity(Gravity.CENTER)
               marginStart = 50
           }
           binding.gridLayout.addView(button,layoutParams)
           if(columnCount == 3)
                   rowCount++
           columnCount++
       }
   }
}