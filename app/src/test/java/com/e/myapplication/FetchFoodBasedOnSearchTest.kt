package com.e.myapplication

import com.e.myapplication.Utilities.MyUtils
import org.junit.Test

import org.junit.Assert.*

/**
 *  allMyFoodList: ArrayList<Map<String, Any>>) : ArrayList<Any>
 */
class FetchFoodBasedOnSearchTest {

    @Test
    fun foodList1_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input: ArrayList<Map<String, Any>> = arrayListOf()
        var searchMatchList: ArrayList<Map<String, Any>> = arrayListOf()

        searchMatchList.add(mapOf("name" to "Apple pie"))
        searchMatchList.add(mapOf("name" to "Apple juice"))
        searchMatchList.add(mapOf("name" to "Apple sauce"))

        input.addAll(searchMatchList)
        input.add(mapOf("name" to "Big Mac"))
        input.add(mapOf("name" to "Cheereburger"))

        var functionOutput = myUtils.fetchRequestedFoodFromAllFoods("Apple", input)
        assertTrue(
            functionOutput.containsAll(searchMatchList) && functionOutput.size == searchMatchList.size
        )
    }

    @Test
    fun foodList2_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input: ArrayList<Map<String, Any>> = arrayListOf()
        var searchMatchList: ArrayList<Map<String, Any>> = arrayListOf()

        searchMatchList.add(mapOf("name" to "pie apple pie"))
        searchMatchList.add(mapOf("name" to "juicapple"))
        searchMatchList.add(mapOf("name" to "apple"))

        input.addAll(searchMatchList)
        input.add(mapOf("name" to "appl"))
        input.add(mapOf("name" to "appl e"))
        input.add(mapOf("name" to "cheeserburger appl"))
        input.add(mapOf("name" to 2))
        input.add(mapOf("name" to true ))

        var functionOutput = myUtils.fetchRequestedFoodFromAllFoods("Apple", input)
        assertTrue(
            functionOutput.containsAll(searchMatchList) && functionOutput.size == searchMatchList.size
        )

    }
}