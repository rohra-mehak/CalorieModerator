package com.e.myapplication

import com.e.myapplication.Utilities.MyUtils
import org.junit.Test

import org.junit.Assert.*

/**
 *
 */
class WeightTest {

    @Test
    fun weight_isValid() {
        var myUtils: MyUtils = MyUtils()
        assertTrue(myUtils.validateWeightString("60.0"))
        assertTrue(myUtils.validateWeightString("153.6"))
        assertTrue(myUtils.validateWeightString("45"))
        assertTrue(myUtils.validateWeightString("56.3256325"))
        assertTrue(myUtils.validateWeightString("126"))
    }

    @Test
    fun weight_isInvalid() {
        var myUtils: MyUtils = MyUtils()
        assertFalse(myUtils.validateWeightString("0.0"))
        assertFalse(myUtils.validateWeightString("50-20"))
        assertFalse(myUtils.validateWeightString(".50"))
        assertFalse(myUtils.validateWeightString("None"))
        assertFalse(myUtils.validateWeightString("Null"))
        assertFalse(myUtils.validateWeightString("300.0"))
        assertFalse(myUtils.validateWeightString("-50.0"))
        assertFalse(myUtils.validateWeightString("0.1"))
        assertFalse(myUtils.validateWeightString("10.999"))
    }
}