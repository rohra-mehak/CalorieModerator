package com.e.myapplication.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class UserEntity (
    val username: String? = "",
    val dateOfBirth: String? = "",
    val gender: String? = "",
    val height: Int = 0,
    var weightByDate: MutableList<WeightByDateEntity> = ArrayList(),
    var foodConsumedByDate: MutableList<FoodConsumedByDayEntity> = ArrayList()
)