package com.e.myapplication

import com.e.myapplication.Utilities.MyUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * user@domain
The maximum total length of a user name is 64 characters.
Maximum of 255 characters in the domain part (the one after the “@”)


https://gist.github.com/cjaoude/fd9910626629b53c4d25
 */
class EmailTest {

    @Test
    fun email_isValid() {
        var myUtils: MyUtils = MyUtils()
        assertTrue(myUtils.isEmailValid("test@test.test"))
        assertTrue(myUtils.isEmailValid("adr1243567890ian@gmail.t"))
        assertTrue(myUtils.isEmailValid("a@b.c"))
        assertTrue(myUtils.isEmailValid("email@lastnameveryverylonghehehehehehe.com"))
        assertTrue(myUtils.isEmailValid("email@example.museum"))
        assertTrue(myUtils.isEmailValid("firstname-lastname@example.com"))
        assertTrue(myUtils.isEmailValid("firstname_lastname@example.com"))

    }

    @Test
    fun email_isInvalid() {
        var myUtils: MyUtils = MyUtils()
        assertFalse(myUtils.isEmailValid("test"))
        assertFalse(myUtils.isEmailValid("#@%^%#\$@#\$@#.com"))
        assertFalse(myUtils.isEmailValid("@example.com"))
        assertFalse(myUtils.isEmailValid("@example"))
        assertFalse(myUtils.isEmailValid("1325@123.pl"))
        assertFalse(myUtils.isEmailValid("!@#email@example.com"))
        assertFalse(myUtils.isEmailValid("email@@email.com"))
        assertFalse(myUtils.isEmailValid("Email@email.com.com"))
        assertFalse(myUtils.isEmailValid("firstname@lstrname.com@.mail.pl"))

    }
}