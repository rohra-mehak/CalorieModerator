package com.e.myapplication

import com.e.myapplication.Utilities.MyUtils
import org.junit.Test

import org.junit.Assert.*
import java.lang.IllegalArgumentException

/**
 * user@domain
The maximum total length of a user name is 64 characters.
Maximum of 255 characters in the domain part (the one after the “@”)


https://gist.github.com/cjaoude/fd9910626629b53c4d25
 */
class BmiTest {

    @Test
    fun bmi_isEqual() {
        var myUtils: MyUtils = MyUtils()
        assertEquals(24.7, myUtils.calculateBmi(180, 80.0), 0.1)
        assertEquals(61.7, myUtils.calculateBmi(180, 200.0), 0.1)
        assertEquals(12.3, myUtils.calculateBmi(180, 40.0), 0.1)
        assertEquals(100.0, myUtils.calculateBmi(100, 100.0), 0.1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun bmi_invalidHeight(){
        var myUtils: MyUtils = MyUtils()
        myUtils.calculateBmi(-1, 80.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun bmi_invalidWeight(){
        var myUtils: MyUtils = MyUtils()
        myUtils.calculateBmi(180, 0.0)
    }

}