package com.e.myapplication.entity

class FoodConsumedByDayEntity (
    var date: String? = "",
    var listOfFoodConsumed: MutableList<ConsumedFoodEntity> = ArrayList()
)