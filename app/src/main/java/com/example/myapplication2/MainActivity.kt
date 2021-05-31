package com.example.myapplication2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val myBtn  = findViewById<Button>(R.id.btnclickme)
        myBtn.setOnClickListener {view ->
            clickDatePicker(view)

        }


    }
    fun clickDatePicker(view:View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,
                "you pick year $selectedYear month ${selectedMonth + 1} and date $selectedDayOfMonth" ,
                Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth +1}/$selectedYear"
            val myDateSelected = findViewById<TextView>(R.id.showdatewepick)
            myDateSelected.setText(selectedDate)

            val stf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH )

            val theDate = stf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time/60000

            val currentDate = stf.parse((stf.format(System.currentTimeMillis())))

            val currentDateInMinutes = currentDate!!.time/60000

            val diffrenceInMinutes = currentDateInMinutes- selectedDateInMinutes

            val myDateOutMin = findViewById<TextView>(R.id.showmin)
            myDateOutMin.setText(diffrenceInMinutes.toString())

        }
                ,year
                ,month
                ,day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}