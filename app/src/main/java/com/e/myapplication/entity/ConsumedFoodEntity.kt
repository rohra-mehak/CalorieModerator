package com.e.myapplication.entity

import com.google.firebase.database.Exclude

class ConsumedFoodEntity {
    var quantity: String? = ""
    var name: String? = ""
    var kcal: Int = 0
    var carb: Int = 0
    var protein: Int = 0
    var fat: Int = 0
    var unitOfMeasure: String = ""

    constructor(){

    }

    constructor(quantity: String?, name: String? = "", kcal: Int = 0,carb: Int = 0, protein: Int = 0,fat: Int = 0, unitOfMeasure: String = ""){
        this.name = name
        this.kcal = kcal
        this.carb = carb
        this.protein = protein
        this.fat = fat
        this.unitOfMeasure = unitOfMeasure
        this.quantity = quantity
    }

    @Exclude
    fun toMap(): Map<String, Any>{
        val result = HashMap<String, Any>()
        result.put("quantity", quantity!!)
        result.put("name", name!!)
        result.put("kcal", kcal)
        result.put("carb", carb)
        result.put("protein", protein)
        result.put("fat", fat)
        result.put("unitOfMeasure", unitOfMeasure)

        return result
    }
}

