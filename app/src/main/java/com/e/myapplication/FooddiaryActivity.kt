package com.e.myapplication

import android.R.*
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.collection.LLRBNode
import org.eazegraph.lib.charts.PieChart
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FooddiaryActivity : AppCompatActivity() {
    lateinit var nextButton: Button
    lateinit var bottomProfile: ImageView
    lateinit var testMenu: ImageView
    lateinit var resultMapForUser: MutableMap<String, ArrayList<Map<String, Any>>>
    lateinit var myFoodView: ListView
    lateinit var myPieChart: com.github.mikephil.charting.charts.PieChart

    protected  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fooddiary_layout)

        nextButton = findViewById<Button>(R.id.button_enterweight)
        bottomProfile = findViewById<ImageView>(R.id.bottom_profile)
        testMenu = findViewById<ImageView>(R.id.bottom_test)
        myFoodView = findViewById(R.id.scrollView2)
        resultMapForUser = mutableMapOf()
        myPieChart = findViewById(R.id.pie_chart)
        myPieChart.setUsePercentValues(true)

        bottomProfile.setOnClickListener {
            val intent = Intent(this, EnterweightActivity::class.java)
            finish()
            startActivity(intent)
        }

        testMenu.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
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



        val userId = FirebaseAuth.getInstance().currentUser?.uid
        println("My user ID : "+userId)
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val foodListUserRef: DatabaseReference =
            database.child("user_info").child(userId!!).child("listOfFoodByDay")
        foodListUserRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                resultMapForUser = mutableMapOf()
                for (postSnapshot in dataSnapshot.children) {
                    println("KEY: " + postSnapshot.key)
                    var arrayForGivenDate = resultMapForUser[postSnapshot.key]
                    if (arrayForGivenDate == null)
                        arrayForGivenDate = arrayListOf()
                    for (entry in postSnapshot.children) {
                        arrayForGivenDate.add(entry.value as Map<String, Any>)
                    }
                    println("array" + arrayForGivenDate)
                    postSnapshot.key?.let { resultMapForUser.put(it, arrayForGivenDate) }
                    println("result"+resultMapForUser)
                    // list in listView Myfood diary
                    setListByDate()
                        // and the pie chart
                    setPieChart(pieChartEntries())
                }

            }
        })


    }
/// set our lisview from the list of food for user by the day
    fun setListByDate(){
        println("result............"+resultMapForUser)
        val dateToFood: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
         println(dateToFood)
         var list : ArrayList<Map<String, Any>>? = resultMapForUser[dateToFood]
         var newlist :  ArrayList<Any> = arrayListOf()
         if (list != null) {
             for (maps in list){
            newlist.add(maps.values)
         }
     }
        println( "mylist: " +list)
        println(list)

       val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_2,android.R.id.text1, newlist )

    myFoodView.adapter = adapter
        adapter?.notifyDataSetChanged()
        myFoodView.setOnItemClickListener { parent, view, position, id ->
            // Get the selected item text from ListView
            val selectedItem = parent.getItemAtPosition(position)
            Toast.makeText(this, selectedItem.toString() + "!", Toast.LENGTH_SHORT)
                .show()

        }
    }
   // get entries for pie chart
    fun pieChartEntries(): ArrayList<Float>{
        var carb :Long  = 0
        var fat :Long  = 0
        var protein  :Long  = 0
        var kcal : Long = 0

        val dateToFood: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        println(dateToFood)
        var list : ArrayList<Map<String, Any>>? = resultMapForUser[dateToFood]

        if (list != null) {
            for (ll in list){
                 // they need to be Long no other conversion is accepted here
                carb += ll["carb"] as Long
                fat  += ll["fat"] as Long
                protein += ll["protein"] as Long
                kcal += ll["kcal"] as Long



            }
        }
       //pie chart only accepts float vals
        var newlist :  ArrayList<Float> = arrayListOf(carb.toFloat(), fat.toFloat(), protein.toFloat() , kcal.toFloat())
        println(newlist)
        return newlist

    }
    fun setPieChart( pieList : ArrayList<Float>){
        var entry : ArrayList<PieEntry> = ArrayList<PieEntry>()
        entry.add(PieEntry(pieList[0] , "Fat"))
        entry.add(PieEntry(pieList[1], "Carbs"))
        entry.add(PieEntry(pieList[2],"Protein"))
        val  mySet  = PieDataSet(entry, "Consumption")

          var myData  = PieData(mySet)
        mySet.setColors(*ColorTemplate.JOYFUL_COLORS)

        myPieChart.setDrawCenterText(true);


        myPieChart.setCenterTextColor(Color.BLACK);
        myPieChart.setCenterTextSize(10f);
        myPieChart.setCenterText("kcal:"+ pieList[3].toDouble());
        myPieChart.data = myData


    }
}






