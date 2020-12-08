package com.e.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.repository.TestRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AddfoodActivity : AppCompatActivity() {
    lateinit var searchView: AutoCompleteTextView
    lateinit var quantityView: AutoCompleteTextView
    var fetchFood: Button? = null
    lateinit var bottomFood: ImageView
    lateinit var allFoodList: ArrayList<Map<String, Any>>
    lateinit var myListView: ListView
    lateinit var testEntityRepository: TestRepository


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addfood)

        bottomFood = findViewById<ImageView>(R.id.bottom_food)
        searchView = findViewById(R.id.search_input)
        quantityView = findViewById(R.id.quantity_input)
        fetchFood = findViewById<Button>(R.id.fetch_food_button)

        myListView = findViewById(R.id.scrollView3)
        testEntityRepository = TestRepository()
        //myListView.adapter = ListExampleAdapter(this)

        bottomFood.setOnClickListener {
            val intent = Intent(this, FooddiaryActivity::class.java)
            finish()
            startActivity(intent)
        }


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

        println(allFoodList)


        setUP ()


    }


    // looks for button press on this ">" button
     fun setUP (){
         fetchFood?.setOnClickListener {
             Log.d("bnkbn", "button pressed")
             // creates a new list from search result everytime a search is implemented  and is fed to the adapter
             var myList : ArrayList<Map<String ,Any>> = getAnother()

             val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_2,android.R.id.text1, myList )
             myListView.setAdapter(adapter)
             adapter.notifyDataSetChanged()
             myListView.setOnItemClickListener { parent, view, position, id ->
                 // Get the selected item text from ListView
                 val selectedItem : Map<String, Any> = parent.getItemAtPosition(position) as Map<String, Any>
                 Toast.makeText(this, "$selectedItem  Bitch", Toast.LENGTH_SHORT)
                     .show()
                 //alert is called here with the item user selects to save to db
                 showAlert(selectedItem as Map<String, Any>)
             }


         }
     }
    fun getSearchResult(): ArrayList<Any> {
            var newList: ArrayList<Any>
            newList = arrayListOf()
        var searchResult = searchView.text.toString()
        var quantityResult = quantityView.text.toString()
        if (searchResult.equals("") || (quantityResult.equals(""))) {
            Toast.makeText(this, "Enter something please", Toast.LENGTH_SHORT).show()
            println("return")
        }
        else {
            for (myList in allFoodList) {
                         if (myList.containsValue(searchResult)) {
                         newList.add(myList.values)

                         println(newList)
                     }
                }


            }
        return newList
    }

    // fetches a list from allFoodList that matches search searchResult

    fun getAnother() : ArrayList<Map<String , Any>>{
        var newList: ArrayList<Map<String , Any>> = arrayListOf()
        var searchResult = searchView.text.toString()
        var quantityResult = quantityView.text.toString()
        if (searchResult.equals("") || (quantityResult.equals(""))) {
            Toast.makeText(this, "Enter some shit bitch", Toast.LENGTH_SHORT).show()
            println("return")
        }
        else {
            for (myList in allFoodList) {
                var v = myList["name"].toString()
                if (v.contains(searchResult, true)) {
                    newList.add(myList)
                    println(newList)
                }
           }
        }
            return newList

    }


     // alert for confirmation for adding food to db
    private fun showAlert( item : Map<String , Any>){
        val builder = AlertDialog.Builder(this)
            .setTitle("would you like to add food to diary ?")

        .setPositiveButton("YES") { dialog, which ->
            val dateToFood: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

                val userId = FirebaseAuth.getInstance().currentUser?.uid
                var foodToSave: MutableMap<String, Any> = item as MutableMap<String, Any>
                foodToSave.put("quantity", 2)
                testEntityRepository.writeFoodToUserByDay(userId, dateToFood, foodToSave)

        }
                .setNegativeButton("NO"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked no",Toast.LENGTH_LONG).show()
            }

            .show()


    }






//Testing , does not work // do not remove this code , nor uncomment

    /* private class ListExampleAdapter(context: Context) : BaseAdapter() {
        lateinit var sList: ArrayList<Any>
        private  val mContext : Context

        init {
            mContext = context
        }

        //  no of rows in my listview
        override fun getCount(): Int {
            return sList.size
        }

        override fun getItem(position: Int): Any {
            return sList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        //Responsible for rendering out each row in my list view
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

         val textView = TextView(mContext)
            textView.text =
            return textView
        }

    }*/

}




