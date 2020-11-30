package com.e.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.Constants.Companion.USER_DAY
import com.e.myapplication.Constants.Companion.USER_MONTH
import com.e.myapplication.Constants.Companion.USER_YEAR

class FooddiaryActivity : AppCompatActivity() {
    lateinit var nextButton: Button

    protected  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fooddiary_layout)

        nextButton = findViewById<Button>(R.id.addfood_button)

        nextButton.setOnClickListener{

            val intent = Intent(this, AddfoodActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}