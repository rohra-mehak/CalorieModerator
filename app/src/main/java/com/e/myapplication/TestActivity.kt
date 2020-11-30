package com.e.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.e.myapplication.repository.TestRepository

class TestActivity : AppCompatActivity() {

    lateinit var saveButton: Button
    lateinit var nameInput: TextView
    lateinit var ageInput: TextView
    lateinit var testEntityRepository: TestRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        testEntityRepository = TestRepository()

        saveButton = findViewById<Button>(R.id.test_save)
        nameInput = findViewById<TextView>(R.id.test_name)
        ageInput = findViewById<TextView>(R.id.test_age)

        saveButton.setOnClickListener {
            saveEntity()
        }
    }

    fun saveEntity(){
        val name = nameInput.text.toString()
        val age = ageInput.text.toString().toInt()

        testEntityRepository.writeTest(name, age)
    }
}