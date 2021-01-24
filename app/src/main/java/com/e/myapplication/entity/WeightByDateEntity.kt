package com.e.myapplication.entity

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class WeightByDateEntity {
    var date: String? = ""
    var weight: Double = 0.0

    constructor(){

    }

    constructor(date: String?, weight: Double){
        this.date = date
        this.weight = weight
    }


    @Exclude
    fun toMap(): Map<String, Any>{
        val result = HashMap<String, Any>()
        result.put("date", date!!)
        result.put("weight", weight)

        return result
    }

    @Exclude
    override fun toString(): String {
        return "WeightByDateEntity(date=$date, weight=$weight)"
    }
}
