package com.e.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.Constants.Companion.USER_DAY
import com.e.myapplication.Constants.Companion.USER_MONTH
import com.e.myapplication.Constants.Companion.USER_YEAR
import com.e.myapplication.repository.TestRepository
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.roundToLong

class QuestionnaireActivity : AppCompatActivity() {

    lateinit var nextButton: Button
    lateinit var weightInput: AutoCompleteTextView
    lateinit var heightInput: AutoCompleteTextView
    lateinit var genderRadio: RadioGroup
    lateinit var testEntityRepository: TestRepository

    protected  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questionnaire)

        testEntityRepository = TestRepository()

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
        val dob = userYear.toString()+"-"+userMonth.toString()+"-"+userDay.toString()

        val userId = intent.getStringExtra("USER_ID")
        val userWeight : String = weightInput.text.toString()
        val userHeight : String = heightInput.text.toString()

        val genderRadio = resources.getResourceEntryName(genderRadio.checkedRadioButtonId)
        var gender = ""
        if(genderRadio == "radio_male")
            gender = "Male"
        else
            gender = "Female"

        handleBmi(view, userHeight.toInt(), userWeight.toInt())

        // TODO pass username from previous activities + handle gender radio button
        testEntityRepository.writeInfoToUser(userId, "TODO", dob, gender, userHeight.toInt())

        val intent = Intent(this, FooddiaryActivity::class.java)
        startActivity(intent)

        // TODO remove it, im just testing repositories
        //val intent = Intent(this, TestActivity::class.java)
        //startActivity(intent)
    }

    fun handleBmi(view: View, height: Int, weight: Int){
        var bmi: Double = Math.round(10.0 * (weight.toDouble() / ( height.toDouble()/100*height.toDouble()/100)))/10.0


        // adjust alert message to bmi value
        var alertTitle = ""
        var alertMessage = ""
        var icon = android.R.drawable.ic_dialog_alert
        if(bmi <= 16){
            alertTitle = "BMI: " + bmi + " You are severely thin"
            alertMessage = "Please contact a doctor"
            icon = android.R.drawable.ic_dialog_alert
        } else if (bmi > 16 && bmi <= 18.5){
            alertTitle = "BMI: " + bmi + " You are moderately thin"
            alertMessage = "That's okay, we will help You achieve your goals!"
            icon = android.R.drawable.ic_dialog_info
        } else if (bmi > 18.5 && bmi <= 25){
            alertTitle = "BMI: " + bmi + " You are in perfect shape"
            alertMessage = "We will help you to stay healthy, just as you are right now!"
            icon = android.R.drawable.ic_dialog_info
        } else if (bmi > 25 && bmi <= 35){
            alertTitle = "BMI: " + bmi + " You are a little overweight"
            alertMessage = "That's okay, we will help You achieve your goals!"
            icon = android.R.drawable.ic_dialog_info
        } else {
            alertTitle = "BMI: " + bmi + " You are severly obese"
            alertMessage = "Please contact a doctor"
            icon = android.R.drawable.ic_dialog_alert
        }

        basicAlert(view, alertMessage, alertTitle, icon)
    }
    fun basicAlert(view: View, message: String, title: String, icon: Int){

        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(icon)

        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
            Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
        }
        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
            Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}


