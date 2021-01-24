package com.e.myapplication

import com.e.myapplication.Utilities.MyUtils
import org.junit.Test

import org.junit.Assert.*
import java.lang.IllegalArgumentException


class MonthTest {

    @Test
    fun date_isCorrect() {
        var myUtils: MyUtils = MyUtils()
        assertEquals("1-1-2000", myUtils.generateDateFormated(1, 1, 2000))
        assertEquals("31-12-2000", myUtils.generateDateFormated(31, 12, 2000))
        assertEquals("1-1-1999", myUtils.generateDateFormated(1, 1, 1999))
        assertEquals("15-1-2021", myUtils.generateDateFormated(15, 1, 2021))
        assertEquals("30-5-2020", myUtils.generateDateFormated(30, 5, 2020))
    }

    @Test(expected = IllegalArgumentException::class)
    fun date_dayZero(){
        var myUtils: MyUtils = MyUtils()
        myUtils.generateDateFormated(0, 1, 2000)
    }

    @Test(expected = IllegalArgumentException::class)
    fun date_dayMinus(){
        var myUtils: MyUtils = MyUtils()
        myUtils.generateDateFormated(-1, 1, 2000)
    }
    @Test(expected = IllegalArgumentException::class)
    fun date_monthZero(){
        var myUtils: MyUtils = MyUtils()
        myUtils.generateDateFormated(1, 0, 2000)
    }
    @Test(expected = IllegalArgumentException::class)
    fun date_monthMinus(){
        var myUtils: MyUtils = MyUtils()
        myUtils.generateDateFormated(1, -1, 2000)
    }
    @Test(expected = IllegalArgumentException::class)
    fun date_yearMinus(){
        var myUtils: MyUtils = MyUtils()
        myUtils.generateDateFormated(1, 1, -2000)
    }
    @Test(expected = IllegalArgumentException::class)
    fun date_yearTooSmall(){
        var myUtils: MyUtils = MyUtils()
        myUtils.generateDateFormated(1, 1, 1890)
    }
}