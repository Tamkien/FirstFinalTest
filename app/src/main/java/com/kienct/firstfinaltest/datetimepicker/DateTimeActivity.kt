package com.kienct.firstfinaltest.datetimepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kienct.firstfinaltest.R
import com.kienct.firstfinaltest.qrreading.QRReadingActivity
import kotlinx.android.synthetic.main.activity_date_time.*
import java.util.*

class DateTimeActivity : AppCompatActivity() {
    private fun Date.add(field: Int, amount: Int): Date = Calendar.getInstance().let { calendar ->
        calendar.time = this@add
        calendar.add(field, amount)
        return@let calendar.time
    }

    private val c: Calendar = Calendar.getInstance()
    private var today: Calendar = Calendar.getInstance()
    private val dummyCalendar = Calendar.getInstance()

    //when date is set
    private var dateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            btDate.text =
                String.format(
                    "Ngày %02d tháng %02d năm %d",
                    dayOfMonth,
                    monthOfYear + 1,//months start with 0 so +1
                    year
                )
            dummyCalendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0)// to compare
        }

    //when time is set
    private var timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        btTime.text =
            String.format("%02d giờ %02d phút", hour, minute)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time)
        today.set(
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH),
            0,
            0,
            0
        )
        dummyCalendar.set(
            Calendar.DATE,
            c.get(Calendar.DATE) - 1
        ) // assume that at first dummyCalendar is after c
        btDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, 0)
            datePickerDialog.setOnDateSetListener(dateSetListener)
            //min date is today, range is 2 weeks/14 days
//            datePickerDialog.datePicker.minDate = c.timeInMillis
//            datePickerDialog.datePicker.maxDate = c.time.add(Calendar.DATE, 13).time
            datePickerDialog.show()
        }
        btTime.setOnClickListener {
            //if datePicker picked today, timePicker focused on 8, else on 10
            val hour = when (dummyCalendar.time) {
                today.time -> 10
                today.time.add(Calendar.DAY_OF_MONTH, 1) -> {
                    6
                }
                else -> 0
            }
            val timePickerDialog = TimePickerDialog(
                this, timeSetListener, hour, 0, true
            )
            timePickerDialog.show()
        }
        btNext2.setOnClickListener {
            val intent = Intent(this, QRReadingActivity::class.java)
            startActivity(intent)
        }
    }
}