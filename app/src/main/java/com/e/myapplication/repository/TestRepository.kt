package com.e.myapplication.repository

import com.e.myapplication.entity.*
import com.google.firebase.database.*


class TestRepository {
    private lateinit var database: DatabaseReference

    constructor(){

    }

    @Deprecated("This method was only meant for testing - do not use")
    fun writeTest(name: String, age: Int){
        println("starting write test")
        // Database reference pointing to root of database
        database = FirebaseDatabase.getInstance().reference
        // Database reference pointing to demo node
        val entityRef: DatabaseReference = database.child("user_info")
       // demoRef.setValue("test3")
        val entityId = database.push().key
        val toStore = UserEntity()
        entityRef.child(entityId!!).setValue(toStore)
    }

    fun writeInfoToUser(userId: String?, username: String? = "", dateOfBirth: String? = "", gender: String? = "", height: Int = 0){
        database = FirebaseDatabase.getInstance().reference
        val entityRef: DatabaseReference = database.child("user_info")
//        val entityId = database.push().key
        val toStore = UserEntity(username, dateOfBirth, gender, height)

        entityRef.child(userId!!).setValue(toStore)
    }

    fun writeWeightByDayToUser(userId: String?, date: String?, weight: Int){
        database = FirebaseDatabase.getInstance().reference
        val weightListRef: DatabaseReference = database.child("user_info").child(userId!!).child("weightByDate")
        val entityId = database.push().key
        val toStore = WeightByDateEntity(date, weight)

        weightListRef.child(entityId!!).setValue(toStore)
    }

    fun getWeightListForUser(userId: String?): ArrayList<Map<String, Any>>{
        database = FirebaseDatabase.getInstance().reference
        val weightListRef: DatabaseReference = database.child("user_info").child(userId!!).child("weightByDate")
        var resultList = arrayListOf<Map<String, Any>>()
        println("Startin fetching")
        weightListRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var resultList = arrayListOf<Map<String, Any>>()
                for (postSnapshot in dataSnapshot.children) {
                    resultList.add(postSnapshot.value as Map<String, Any>)
                }
                print(resultList)
            }

        })
        return resultList
    }

    fun writeFoodToUserByDay(userId: String?, date: String?, food: Map<String, Any>){
        database = FirebaseDatabase.getInstance().reference
        val foodListRef: DatabaseReference = database.child("user_info").child(userId!!).child("listOfFoodByDay").child(date!!)
        val entityId = database.push().key

        foodListRef.child(entityId!!).setValue(food)
    }

    /*
    * Returns list of maps like that:
    * [
    *   "date1": [{food, quantiy}, {food2, quantity2}],
    *   "date2": [{food3, quantiy}, {food4, quantity2}],
    * ]
     */
    fun getFoodListByDateForUser(userId: String?): ArrayList<Map<String, Any>>{
        database = FirebaseDatabase.getInstance().reference
        val foodListRef: DatabaseReference = database.child("user_info").child(userId!!).child("listOfFoodByDay")
        var resultList = arrayListOf<Map<String, Any>>()
        println("Startin fetching")
        foodListRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    resultList.add(postSnapshot.value as Map<String, Any>)
                }
            }
        })
        return resultList
    }


    fun writeFood(name: String?, uom: String?, kcal: Int, fat: Int, carb: Int, protein: Int){
        database = FirebaseDatabase.getInstance().reference
        val weightListRef: DatabaseReference = database.child("food")
        val entityId = database.push().key
        val toStore = FoodEntity(name, kcal, carb, protein, fat, uom!!)
        weightListRef.child(entityId!!).setValue(toStore)
    }
    fun getFoodList(): ArrayList<Map<String, Any>> {
        database = FirebaseDatabase.getInstance().reference
        val foodListRef: DatabaseReference = database.child("food")
        var resultList = arrayListOf<Map<String, Any>>()
        println("Startin fetching")
        foodListRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    resultList.add(postSnapshot.value as Map<String, Any>)
                }
            }

        })
        println("!!!" + resultList)
        return resultList
    }

}