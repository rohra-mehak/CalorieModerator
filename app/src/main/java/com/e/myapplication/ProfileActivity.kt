package com.e.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.time.LocalDate


class  ProfileActivity : AppCompatActivity() {

    lateinit var bottomFood: ImageView
    lateinit var thirdButton : ImageView
    lateinit var bottomProfile : ImageView
    lateinit var nameText : TextView
    lateinit var emailText : TextView
    lateinit var weightText : TextView
    lateinit var heightText : TextView
    lateinit var bmiText : TextView
    lateinit var dobText : TextView

    lateinit var logoutButton: Button

    lateinit var userInfoMap: MutableMap<String, Any>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)
        val actionBar = supportActionBar
        actionBar!!.title = "User Information"

        bottomFood = findViewById<ImageView>(R.id.bottom_food)
        thirdButton = findViewById<ImageView>(R.id.third_button)
        bottomProfile = findViewById<ImageView>(R.id.bottom_profile)
        nameText = findViewById<TextView>(R.id.name_disp)
        emailText = findViewById<TextView>(R.id.email_disp)
        weightText = findViewById<TextView>(R.id.weight_disp)
        heightText = findViewById<TextView>(R.id.height_disp)
        bmiText = findViewById<TextView>(R.id.bmi_disp)
        dobText = findViewById<TextView>(R.id.dob_disp)
        logoutButton = findViewById<Button>(R.id.button_logout)

        bottomFood.setOnClickListener {
            val intent = Intent(this, FooddiaryActivity::class.java)
            finish()
            startActivity(intent)
        }

        bottomProfile.setOnClickListener {
            val intent = Intent(this, EnterweightActivity::class.java)
            finish()
            startActivity(intent)
        }
        thirdButton.setOnClickListener{

            val intent = Intent(this, ReportsActivity::class.java)
            finish()
            startActivity(intent)
        }

        logoutButton.setOnClickListener {

        }


        // DB related
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val userRef: DatabaseReference =
            database.child("user_info").child(userId!!)
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                userInfoMap = mutableMapOf()
               // println("\n############### " + dataSnapshot.children)
                for (postSnapshot in dataSnapshot.children) {
               //     println("KEY: " + postSnapshot.key)
             //       println("VALUE: " + postSnapshot.value)
                    postSnapshot.value?.let { userInfoMap.put(postSnapshot.key!!, it) }
                }
                fillFieldsWithDataFromDb()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fillFieldsWithDataFromDb(){
        var recentWeight = getRecentWeight()
        var height = userInfoMap["height"].toString()
        var bmi : String
        if(recentWeight == "Not set yet")
            bmi = "Not set yet"
        else
            bmi = (Math.round(10.0 * (recentWeight.toDouble() / ( height.toDouble()/100*height.toDouble()/100)))/10.0).toString()
        nameText.text = userInfoMap["username"] as CharSequence?
        emailText.text = userInfoMap["gender"] as CharSequence?
        dobText.text = userInfoMap["dateOfBirth"] as CharSequence?
        heightText.text = height
        bmiText.text = bmi.toString()
        weightText.text = recentWeight
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getRecentWeight(): String {
        if (userInfoMap["weightByDate"] == null)
            return "Not set yet"
        var mostRecentDate = LocalDate.of(1, 1, 1)
        var mostRecentWeight = ""
        for(entry in userInfoMap["weightByDate"] as MutableMap<String, MutableMap<String, Any>>){
            var dateInString = entry.value["date"].toString().split("-")
            var currentEntryDate = LocalDate.of(dateInString[2].toInt(), dateInString[1].toInt(), dateInString[0].toInt())
            if(currentEntryDate > mostRecentDate){
                mostRecentDate = currentEntryDate
                mostRecentWeight = entry.value["weight"].toString()
            }
        }
        return mostRecentWeight
    }
//TODO
}