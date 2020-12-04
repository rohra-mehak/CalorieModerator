package com.e.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class FooddiaryActivity : AppCompatActivity() {
    lateinit var nextButton: Button
    lateinit var bottomProfile: ImageView

    protected  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fooddiary_layout)

        nextButton = findViewById<Button>(R.id.button_enterweight)
        bottomProfile = findViewById<ImageView>(R.id.bottom_profile)

        bottomProfile.setOnClickListener {
            val intent = Intent(this, EnterweightActivity::class.java)
            finish()
            startActivity(intent)
        }

        nextButton.setOnClickListener{

            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                println(user.uid)
            }
            val intent = Intent(this, AddfoodActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}