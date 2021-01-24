package com.e.myapplication

import com.e.myapplication.Utilities.MyUtils
import org.junit.Test

import org.junit.Assert.*

/**
 *  userInfoMap: MutableMap<String, MutableMap<String, Any>>
 */
class GetMostRecentDateTest {

    @Test
    fun mostRecent_first_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input:  MutableMap<String, MutableMap<String, Any>>

        input = mutableMapOf(
            "1" to mutableMapOf("date" to "30-12-2021", "weight" to 100), // MOST RECENT
            "2" to mutableMapOf("date" to "29-12-2021", "weight" to 90),
            "3" to mutableMapOf("date" to "2-4-2021", "weight" to 80))


        assertEquals("100", myUtils.getRecentWeight(mutableMapOf("weightByDate" to input)))
    }

    @Test
    fun mostRecent_last_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input:  MutableMap<String, MutableMap<String, Any>>

        input = mutableMapOf(
            "1" to mutableMapOf("date" to "30-12-2021", "weight" to 100),
            "2" to mutableMapOf("date" to "29-12-2021", "weight" to 90),
            "3" to mutableMapOf("date" to "31-12-2021", "weight" to 80)) // MOST RECENT


        assertEquals("80", myUtils.getRecentWeight(mutableMapOf("weightByDate" to input)))
    }

    @Test
    fun mostRecent_middle_isValid() {
        var myUtils: MyUtils = MyUtils()
        var input:  MutableMap<String, MutableMap<String, Any>>

        input = mutableMapOf(
            "1" to mutableMapOf("date" to "30-12-2021", "weight" to 100),
            "2" to mutableMapOf("date" to "31-12-2021", "weight" to 90), // MOST RECENT
            "3" to mutableMapOf("date" to "29-12-2021", "weight" to 80))


        assertEquals("90", myUtils.getRecentWeight(mutableMapOf("weightByDate" to input)))
    }

    @Test
    fun mostRecent_empty_isValid() {
        var myUtils: MyUtils = MyUtils()

        assertEquals("Not set yet", myUtils.getRecentWeight(mutableMapOf()))
    }
}