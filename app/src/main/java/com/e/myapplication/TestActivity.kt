package com.e.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.e.myapplication.Constants.Companion.GENDER_MALE
import com.e.myapplication.repository.TestRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.random.Random

class TestActivity : AppCompatActivity() {

    lateinit var backButton: Button
    lateinit var saveFoodToDb: Button
    lateinit var getWeightListButton: Button
    lateinit var saveWeightButton: Button
    lateinit var weightInput: TextView
    lateinit var dateInput: TextView
    lateinit var dateFoodInput: TextView
    lateinit var foodIdInput: TextView
    lateinit var saveFoodDate: Button
    lateinit var testEntityRepository: TestRepository

    lateinit var allFoodList: ArrayList<Map<String, Any>>
    lateinit var resultMapForUser: MutableMap<String, ArrayList<Any?>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        testEntityRepository = TestRepository()

        backButton = findViewById<Button>(R.id.button_go_back)
        dateInput = findViewById<TextView>(R.id.input_test_date)
        foodIdInput = findViewById<TextView>(R.id.input_test_food_id)
        weightInput = findViewById<TextView>(R.id.input_test_weight)
        saveWeightButton = findViewById<Button>(R.id.button_save_test_date)
        getWeightListButton = findViewById<Button>(R.id.button_test_get_weight)
        saveFoodToDb = findViewById<Button>(R.id.button_test_fillfood)
        saveFoodDate = findViewById<Button>(R.id.button_test_add_food_to_date)
        dateFoodInput = findViewById<TextView>(R.id.input_test_date_food)

        saveFoodToDb.setOnClickListener {
            saveFoodToDb()
        }
        backButton.setOnClickListener {
            val intent = Intent(this, EnterweightActivity::class.java)
            finish()
            startActivity(intent)
        }
        saveWeightButton.setOnClickListener {
            saveWeightEntity()
        }
        getWeightListButton.setOnClickListener {
            getWeightList()
        }
        saveFoodDate.setOnClickListener {
            saveFoodToDate()
        }


        // PASTE IT FOR FOOD LIST UPDATES
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        var database: DatabaseReference
        database = FirebaseDatabase.getInstance().reference
        val foodListRef: DatabaseReference = database.child("food")
        allFoodList = arrayListOf<Map<String, Any>>()
        println("Startin fetching")
        foodListRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    allFoodList.add(postSnapshot.value as Map<String, Any>)
                }
            }

        })


        val foodListUserRef: DatabaseReference = database.child("user_info").child(userId!!).child("listOfFoodByDay")
        resultMapForUser = mutableMapOf()
        foodListUserRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    println("KEY: " + postSnapshot.key)
                    var arrayForGivenDate = resultMapForUser[postSnapshot.key]
                    if(arrayForGivenDate == null)
                        arrayForGivenDate = arrayListOf()
                    for( entry in postSnapshot.children){
                        arrayForGivenDate.add(entry.value)
                    }
                    postSnapshot.key?.let { resultMapForUser.put(it, arrayForGivenDate) }
                }

            }
        })
        // END OF PASTE
    }

    fun saveTestEntity(){
        testEntityRepository.writeInfoToUser("TODO","Adrian", "1999-05-08", GENDER_MALE, 180)
    }
    fun saveWeightEntity(){

        val date = dateInput.text.toString()
        val weight = weightInput.text.toString().toInt()
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        testEntityRepository.writeWeightByDayToUser(userId, date, weight)
    }
    fun getWeightList(){
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        testEntityRepository.getWeightListForUser(userId)
    }
    fun saveFoodToDate(){
        val dateToFood = dateFoodInput.text.toString()
      //  println(allFoodList)
        println(resultMapForUser)
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        var foodToSave: MutableMap<String, Any> = allFoodList[Random.nextInt(0, allFoodList.size)] as MutableMap<String, Any>
        foodToSave.put("quantity", 2)
        testEntityRepository.writeFoodToUserByDay(userId, dateToFood, foodToSave)
    }
    fun saveFoodToDb(){
        testEntityRepository.writeFood("Apple", "piece", 100, 12, 153, 123)
        testEntityRepository.writeFood("Milk", "piece", 256, 42, 183, 113)
        testEntityRepository.writeFood("Cheese", "piece", 178, 82, 113, 3)
        testEntityRepository.writeFood("Pizza", "piece", 642, 13, 133, 73)
        testEntityRepository.writeFood("Cola", "piece", 275, 62, 123, 263)
        testEntityRepository.writeFood("BigMac", "piece", 632, 85, 153, 16)
        testEntityRepository.writeFood("Cheeseburger", "piece", 123, 18, 173, 11)
        testEntityRepository.writeFood("Spaghetti", "piece", 432, 14, 133, 17)
        testEntityRepository.writeFood("Tikka masala", "piece",  760, 111, 163, 13)


    }
}