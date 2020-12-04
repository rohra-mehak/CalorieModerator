package com.e.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.e.myapplication.Constants.Companion.GENDER_MALE
import com.e.myapplication.repository.TestRepository
import com.google.firebase.auth.FirebaseAuth

class TestActivity : AppCompatActivity() {

    lateinit var saveButton: Button
    lateinit var saveFoodToDb: Button
    lateinit var getWeightListButton: Button
    lateinit var saveWeightButton: Button
    lateinit var weightInput: TextView
    lateinit var dateInput: TextView
    lateinit var dateFoodInput: TextView
    lateinit var saveFoodDate: Button
    lateinit var testEntityRepository: TestRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        testEntityRepository = TestRepository()

        saveButton = findViewById<Button>(R.id.test_save)
        dateInput = findViewById<TextView>(R.id.input_test_date)
        weightInput = findViewById<TextView>(R.id.input_test_weight)
        saveWeightButton = findViewById<Button>(R.id.button_save_test_date)
        getWeightListButton = findViewById<Button>(R.id.button_test_get_weight)
        saveFoodToDb = findViewById<Button>(R.id.button_test_fillfood)
        saveFoodDate = findViewById<Button>(R.id.button_test_add_food_to_date)
        dateFoodInput = findViewById<TextView>(R.id.input_test_date_food)

        saveFoodToDb.setOnClickListener {
            saveFoodToDb()
        }
        saveButton.setOnClickListener {
            saveTestEntity()
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
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        testEntityRepository.writeFoodToUserByDay(userId, dateToFood, "foodid toto", 12)
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