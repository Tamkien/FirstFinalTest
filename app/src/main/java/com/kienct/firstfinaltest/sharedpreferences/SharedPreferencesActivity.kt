package com.kienct.firstfinaltest.sharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kienct.firstfinaltest.R
import kotlinx.android.synthetic.main.activity_shared_preferences.*

class SharedPreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
    val prefUser = getSharedPreferences("PREF_USER", MODE_PRIVATE)
        btSubmit.setOnClickListener {
            prefUser.edit().putString("username", etUsername.text.toString()).apply()
            prefUser.edit().putString("password", etPassword.text.toString()).apply()
            val intent = Intent(this, ShowResultActivity::class.java)
            startActivity(intent)
        }
    }
}