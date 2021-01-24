package com.e.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {

   @Rule @JvmField var activityActivityTestRule = ActivityScenarioRule(loginActivty::class.java)

    private val email = "rohramehak@gmail.com"
    private val password = "12345678"
     val stringToast  ="User being logged in !"

    @Test
    fun TestLogin() {
        // check if login input is dispayed
        onView(withId(R.id.login_emailinput))
            .check(
                matches(isCompletelyDisplayed()))  //

        // check if password input is dispayed
        onView(withId(R.id.login_passinput))
            .check(
                matches(isCompletelyDisplayed()))

       // assert view  login button is displayed
        onView(withId(R.id.login_sign_in))
            .check(
                matches(isDisplayed()))

       // perform email input
        onView(withId(R.id.input_email_for_day))
            .perform(typeText(email),
                closeSoftKeyboard())
        //perform passinput
        onView(withId(R.id.login_passinput)).perform(
            ViewActions.typeText(password),
            closeSoftKeyboard()
        )
        //perform a click
        onView(withId(R.id.login_sign_in))
            .perform(ViewActions.click())


        //check if  right toast string  for login sucess is displayed
        onView(withText(stringToast))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))

    }

    //test passes
}