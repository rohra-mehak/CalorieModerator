package com.e.myapplication.repository

import android.provider.ContactsContract
import com.e.myapplication.entity.TestEntity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class TestRepository {
    private lateinit var database: DatabaseReference

    constructor(){

    }
    fun writeTest(name: String, age: Int){
        println("starting write test")
        // Database reference pointing to root of database
        val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference

        // Database reference pointing to demo node
        val demoRef: DatabaseReference = rootRef.child("demo")

        demoRef.setValue("test")
       // val entityId = database.push().key

      //  val toStore = TestEntity(entityId,name, age)

       // testRef.child(entityId!!).setValue(toStore)
    }

}