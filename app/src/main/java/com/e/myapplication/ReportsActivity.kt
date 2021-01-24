package com.e.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ReportsActivity: AppCompatActivity() {

    lateinit var nutritionLine : com.github.mikephil.charting.charts.LineChart
    lateinit var weightline : com.github.mikephil.charting.charts.LineChart
    lateinit var mapOfTotalKcalByDay: MutableMap<String, Int>
    lateinit var mapOfWeightByDay: MutableMap<String, String>
    lateinit var  entry :  ArrayList<Entry>
    lateinit var  entry2 :  ArrayList<Entry>
    lateinit var  bottomFood : ImageView
    lateinit var  bottomProfile : ImageView
    lateinit var  testMenu : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reports_layout)
        mapOfTotalKcalByDay = mutableMapOf()
        mapOfWeightByDay = mutableMapOf()
        entry  = ArrayList<Entry>()
        entry2 = ArrayList<Entry>()
        bottomFood = findViewById<ImageView>(R.id.bottom_food)
        bottomProfile = findViewById<ImageView>(R.id.bottom_profile)
        testMenu = findViewById<ImageView>(R.id.bottom_test)


        val actionBar = supportActionBar
        actionBar!!.title = "Nutrition and Weight Reports "

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
        testMenu.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            finish()
            startActivity(intent)
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference

        val foodListUserRef2: DatabaseReference = database.child("user_info").child(userId!!).child(
            "listOfFoodByDay"
        )
        foodListUserRef2.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mapOfTotalKcalByDay = mutableMapOf()
                for (postSnapshot in dataSnapshot.children) {
                    var totalKcalConsumed: Int = 0
                    for (entry in postSnapshot.children) {
                        var consumed = entry.value as Map<String, Any>
                        totalKcalConsumed += consumed.get("kcal").toString().toInt()
                    }
                    postSnapshot.key?.let { mapOfTotalKcalByDay.put(it, totalKcalConsumed) }
                }
                println("his methos : kcal : :: ")
                println(mapOfTotalKcalByDay)
                nutritionChart()
            }
        })

        val weightListUserRef: DatabaseReference = database.child("user_info").child(userId!!).child(
            "weightByDate"
        )
        weightListUserRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mapOfWeightByDay = mutableMapOf()
                for (postSnapshot in dataSnapshot.children) {
                    println(postSnapshot.value)
                    var weightObj = postSnapshot.value as Map<String, Any>
                    postSnapshot.key?.let {
                        mapOfWeightByDay.put(
                            weightObj["date"].toString(),
                            weightObj["weight"].toString()
                        )
                    }
                }
                println("his method:::")
                println(mapOfWeightByDay)
                weightChart()

            }
        })


        nutritionLine = findViewById(R.id.line_chart)
        weightline = findViewById(R.id.line_chart2)


    }

    fun createNutritionLine():ArrayList<Entry> {
        var index = 0

        for (x in mapOfTotalKcalByDay ) {
            entry.add(Entry(index.toFloat(), x.value.toFloat()))
            index++
        }
        println("mapppppppppp")
        println(mapOfTotalKcalByDay)
        println(entry)
       return entry

    }
    fun nutritionChart(){
        val lineDataSet = LineDataSet(createNutritionLine(), "Nutrition levels by the Day ")

        val iLineDataSets: ArrayList<ILineDataSet> = ArrayList()
        iLineDataSets.add(lineDataSet)

        val lineData = LineData(iLineDataSets)
        nutritionLine.setData(lineData)
        nutritionLine.invalidate()


        // set text if data are are not available
        nutritionLine.setNoDataText("Data not Available")
        lineDataSet.setColor(Color.BLUE)
        lineDataSet.lineWidth = 5F
        lineDataSet.setValueTextColor(Color.BLACK)

    }

    fun createWeightLine():ArrayList<Entry> {
        var index = 0

        for (x in mapOfWeightByDay ) {
            entry2.add(Entry(index.toFloat(), x.value.toFloat()))
            index++
        }
        println("mapppppppppp")
        println(mapOfWeightByDay)
        println(entry2)
        return entry2

    }

    fun weightChart(){
        val lineDataSet = LineDataSet(createWeightLine(), "Weight levels by the Day ")

        val iLineDataSets: ArrayList<ILineDataSet> = ArrayList()
        iLineDataSets.add(lineDataSet)

        val lineData = LineData(iLineDataSets)
        weightline.setData(lineData)
        weightline.invalidate()

        //lineChart.setBackgroundColor(Color.RED)
        //lineChart.setBackgroundColor(Color.RED)

        // set text if data are are not available
       weightline.setNoDataText("Data not Available")
        lineDataSet.setColor(Color.parseColor("#03DAC5"))
        lineDataSet.lineWidth = 5F
        lineDataSet.setValueTextColor(Color.BLACK)

    }


}