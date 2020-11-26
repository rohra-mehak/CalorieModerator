package com.e.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.DatePicker
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.Constants.Companion.USER_DAY
import com.e.myapplication.Constants.Companion.USER_MONTH
import com.e.myapplication.Constants.Companion.USER_YEAR

class QuestionnaireActivity : AppCompatActivity() {

    lateinit var nextButton: Button
    lateinit var weightInput: AutoCompleteTextView
    lateinit var heightInput: AutoCompleteTextView
    lateinit var genderRadio: RadioGroup

    protected  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questionnaire)

        nextButton = findViewById<Button>(R.id.next_questionaire_button)
        weightInput = findViewById<AutoCompleteTextView>(R.id.input_weight)
        heightInput = findViewById<AutoCompleteTextView>(R.id.input_height)
        genderRadio = findViewById<RadioGroup>(R.id.gender_radio_group)


        nextButton.setOnClickListener{
            onNextButtonClicked(it)
        }
    }

    fun onNextButtonClicked(view: View){
        val userYear = intent.getIntExtra(USER_YEAR, 0)
        val userMonth = intent.getIntExtra(USER_MONTH, 0)
        val userDay = intent.getIntExtra(USER_DAY, 0)

        println(weightInput.text)
        println(heightInput.text)
        println(resources.getResourceEntryName(genderRadio.checkedRadioButtonId))
        println(userYear)
        println(userMonth)
        println(userDay)

        // TODO save to db??
    }
}


