package com.e.myapplication

import com.e.myapplication.Utilities.MyUtils
import org.junit.Test

import org.junit.Assert.*

/**
 *  userInfoMap: MutableMap<String, MutableMap<String, Any>>
 */
class GetFoodListForDateTest {

    @Test
    fun foodForDate_first_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input:  MutableMap<String, ArrayList<Map<String, Any>>>

        var desiredResult = arrayListOf(mapOf("name" to "Apple", "kcal" to 1), (mapOf("name" to "Burger", "kcal" to 1)))
        input = mutableMapOf(
                "30-12-2021" to desiredResult,
                "31-12-2021" to arrayListOf(mapOf("name" to "BigMac", "kcal" to 1), (mapOf("name" to "McWrap", "kcal" to 1))),
                "29-12-2021" to arrayListOf(mapOf("name" to "some food", "kcal" to 1), (mapOf("name" to "Wiesmac", "kcal" to 1))))

        assertEquals(desiredResult, myUtils.setListByDate("30-12-2021", input))
    }

    @Test
    fun foodForDate_last_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input:  MutableMap<String, ArrayList<Map<String, Any>>>

        var desiredResult = arrayListOf(mapOf("name" to "Apple", "kcal" to 1), (mapOf("name" to "Burger", "kcal" to 1)))
        input = mutableMapOf(
                "31-12-2021" to arrayListOf(mapOf("name" to "BigMac", "kcal" to 1), (mapOf("name" to "McWrap", "kcal" to 1))),
                "29-12-2021" to arrayListOf(mapOf("name" to "some food", "kcal" to 1), (mapOf("name" to "Wiesmac", "kcal" to 1))),
                "30-12-2021" to desiredResult)

        assertEquals(desiredResult, myUtils.setListByDate("30-12-2021", input))
    }

    @Test
    fun foodForDate_middle_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input:  MutableMap<String, ArrayList<Map<String, Any>>>

        var desiredResult = arrayListOf(mapOf("name" to "Apple", "kcal" to 1), (mapOf("name" to "Burger", "kcal" to 1)))
        input = mutableMapOf(
                "31-12-2021" to arrayListOf(mapOf("name" to "BigMac", "kcal" to 1), (mapOf("name" to "McWrap", "kcal" to 1))),
                "30-12-2021" to desiredResult,
                "29-12-2021" to arrayListOf(mapOf("name" to "some food", "kcal" to 1), (mapOf("name" to "Wiesmac", "kcal" to 1))))

        assertEquals(desiredResult, myUtils.setListByDate("30-12-2021", input))
    }

    @Test
    fun foodForDate_empty_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input:  MutableMap<String, ArrayList<Map<String, Any>>>

        var desiredResult = arrayListOf(mapOf("name" to "Apple", "kcal" to 1), (mapOf("name" to "Burger", "kcal" to 1)))
        input = mutableMapOf(
                "31-12-2021" to arrayListOf(mapOf("name" to "BigMac", "kcal" to 1), (mapOf("name" to "McWrap", "kcal" to 1))),
                "30-12-2021" to desiredResult,
                "29-12-2021" to arrayListOf(mapOf("name" to "some food", "kcal" to 1), (mapOf("name" to "Wiesmac", "kcal" to 1))))

        // EMPTY
        assertTrue(myUtils.setListByDate("01-01-2021", input).isEmpty()) // WRONG DATE <---
    }
}