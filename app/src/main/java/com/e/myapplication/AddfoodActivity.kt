package com.e.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.Constants.Companion.USER_DAY
import com.e.myapplication.Constants.Companion.USER_MONTH
import com.e.myapplication.Constants.Companion.USER_YEAR

class AddfoodActivity : AppCompatActivity() {

    lateinit var bottomFood : ImageView

    protected  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addfood_layout)

        bottomFood = findViewById<ImageView>(R.id.bottom_food)

        bottomFood.setOnClickListener {
            val intent = Intent(this, FooddiaryActivity::class.java)
            finish()
            startActivity(intent)
        }
        // TODO
    }
}