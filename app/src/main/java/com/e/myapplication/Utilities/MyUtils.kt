package com.e.myapplication.Utilities

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Double.parseDouble
import java.lang.IllegalArgumentException
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class MyUtils{
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun isEmailValid(target: CharSequence): Boolean {
        return target.matches(emailPattern.toRegex())
    }

    fun arePasswordsValid(pass1: String, pass2: String): Boolean{
        return pass1 == pass2 && pass2.length > 5 && pass2.length < 40
    }

    fun calculateBmi(height: Int, weight: Double) : Double{
        if(height < 50 || weight < 20)
            throw IllegalArgumentException("Invalid height or weight")
        return Math.round(10.0 * (weight.toDouble() / ( height.toDouble()/100*height.toDouble()/100)))/10.0
    }

    fun validateWeightString(weight: String) : Boolean{
        val num: Double
        try {
            num = parseDouble(weight)
        } catch (e: NumberFormatException) {
            return false
        }
        if(num < 20.0 || num > 200.0)
            return false
        return true
    }

    fun generateDateFormated(day: Int, month: Int, year: Int): String{
        if (day < 1 || day > 31)
            throw IllegalArgumentException("Invalid day number")
        if (month < 1 || month > 12)
            throw IllegalArgumentException("Invalid month number")
        if (year < 1900 || year > 2050)
            throw IllegalArgumentException("Invalid year number")

        return ( day.toString() + "-" + month + "-" + year)
    }

    fun fetchRequestedFoodFromAllFoods(search: String, allMyFoodList: ArrayList<Map<String, Any>>) : ArrayList<Any>{
        var newList: ArrayList<Any> = arrayListOf()
        for (myList in allMyFoodList) {
            var v = myList["name"].toString()
            if (v.contains(search, true)) {
                newList.add(myList)
            //    println(newList)
            }
        }
        return newList
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getRecentWeight(userInfoMap: MutableMap<String, Any>): String {
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

    fun setListByDate(dateToFood: String, resultMapForUser: MutableMap<String, ArrayList<Map<String, Any>>>): ArrayList<Any> {
    //    val dateToFood: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        println(dateToFood)
        var list: ArrayList<Map<String, Any>>? = resultMapForUser[dateToFood]
        var newlist: ArrayList<Any> = arrayListOf()
        if (list != null) {
            for (maps in list) {
                newlist.add(maps)
            }
        }
        return newlist
    }
}