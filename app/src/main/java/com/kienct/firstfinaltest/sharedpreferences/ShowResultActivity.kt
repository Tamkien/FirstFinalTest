package com.kienct.firstfinaltest.sharedpreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kienct.firstfinaltest.R
import kotlinx.android.synthetic.main.activity_show_result.*

class ShowResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)
        val prefUser = getSharedPreferences("PREF_USER", MODE_PRIVATE)
        textView.text = prefUser.getString("username", "Empty")
        textView2.text = prefUser.getString("password", "Empty")
    }
}