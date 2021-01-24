package com.e.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.repository.TestRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fooddiary_layout.*
import java.text.DateFormatSymbols
import java.util.*

class EnterweightActivity : AppCompatActivity() {
    lateinit var acceptWeightButton: Button
    lateinit var bottomFood: ImageView
    lateinit var testMenu: ImageView
    lateinit var thirdButton : ImageView
    lateinit var buttonShowWeight: Button
    lateinit var buttonPickDateForWeight: Button
    lateinit var inputWeight : AutoCompleteTextView
    lateinit var testEntityRepository: TestRepository

    lateinit var dateToSave: String



    protected  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enterweight_layout)
        val actionBar = supportActionBar
        actionBar!!.title = "Enter Your Weight"

        testEntityRepository = TestRepository()

        bottomFood = findViewById<ImageView>(R.id.bottom_food)
        testMenu = findViewById<ImageView>(R.id.bottom_test)
        thirdButton = findViewById<ImageView>(R.id.third_button)
        acceptWeightButton = findViewById<Button>(R.id.button_enterweight)
        buttonShowWeight = findViewById<Button>(R.id.button_show_weight)
        inputWeight = findViewById<AutoCompleteTextView>(R.id.input_weight_for_day)
        buttonPickDateForWeight = findViewById<Button>(R.id.button_pick_date_for_weight)


        bottomFood.setOnClickListener {
            val intent = Intent(this, FooddiaryActivity::class.java)
            finish()
            startActivity(intent)
        }

        testMenu.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            finish()
            startActivity(intent)
        }

        thirdButton.setOnClickListener{

            val intent = Intent(this, ReportsActivity::class.java)
            finish()
            startActivity(intent)
        }


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        buttonPickDateForWeight.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                buttonPickDateForWeight.setText("" + dayOfMonth + " " + getMonth(monthOfYear) + ", " + year)
                dateToSave = (dayOfMonth.toString() + "-" + (monthOfYear+1) + "-" + year)
            }, year, month, day)

            dpd.show()
        }



        inputWeight.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonShowWeight.text = s.toString()
            }

        })

        acceptWeightButton.setOnClickListener{

            val user = FirebaseAuth.getInstance().currentUser
            var userId: String = ""
            if (user != null) {
                userId = user.uid
            }

            val weightToSave = buttonShowWeight.text

            println(dateToSave)
            println(weightToSave)
            println(userId)

            val weight = weightToSave.toString().toDouble()

            if(weight < 10.0 || weight > 200){
                Toast.makeText(this, "Please enter correct weight! ", Toast.LENGTH_SHORT)
                    .show()
            } else{
                testEntityRepository.writeWeightByDayToUser(userId, dateToSave, weightToSave.toString().toDouble())
                Toast.makeText(this, "Weight saved! ", Toast.LENGTH_SHORT)
                    .show()
            }
        }


//        nextButton = findViewById<Button>(R.id.addfood_button)
//
//        nextButton.setOnClickListener{
//
//            val user = FirebaseAuth.getInstance().currentUser
//            if (user != null) {
//                println(user.uid)
//            }
//            val intent = Intent(this, AddfoodActivity::class.java)
//            finish()
//            startActivity(intent)
//        }
    }

    fun getMonth(num: Int): String{
        var month = "wrong"
        val dfs = DateFormatSymbols()
        val months: Array<String> = dfs.getMonths()
        if (num >= 0 && num <= 11) {
            month = months[num]
        }
        return month
    }
}


