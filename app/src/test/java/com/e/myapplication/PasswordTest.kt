package com.e.myapplication

import com.e.myapplication.Utilities.MyUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Password 5 > length > 40
 */
class PasswordTest {

    @Test
    fun password_isValid() {
        var myUtils: MyUtils = MyUtils()
        assertTrue(myUtils.arePasswordsValid("password", "password"))
        assertTrue(myUtils.arePasswordsValid("123456", "123456"))
        assertTrue(myUtils.arePasswordsValid("!@#$%^", "!@#$%^"))
        assertTrue(myUtils.arePasswordsValid("gjS26iHdw37-;Gdsw3", "gjS26iHdw37-;Gdsw3"))
        assertTrue(myUtils.arePasswordsValid("static", "static"))
    }

    @Test
    fun password_isInvalid() {
        var myUtils: MyUtils = MyUtils()
        assertFalse(myUtils.arePasswordsValid("password", "passwordd"))
        assertFalse(myUtils.arePasswordsValid("password", "passwor"))
        assertFalse(myUtils.arePasswordsValid("12345", "12345"))
        assertFalse(myUtils.arePasswordsValid("", ""))
        assertFalse(myUtils.arePasswordsValid("password", ""))
        assertFalse(myUtils.arePasswordsValid("", "password"))
        assertFalse(myUtils.arePasswordsValid("passwordpasswordpasswordpasswordpasswordpassword", "passwordpasswordpasswordpasswordpasswordpassword"))
        assertFalse(myUtils.arePasswordsValid("123456", "12345"))
        assertFalse(myUtils.arePasswordsValid("password", "passwordpasswodpasswordpasswordpasswordpasswordpasswordrdpasswordpasswordpasswordpassword"))


    }
}