package com.e.myapplication


import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.matcher.BundleMatchers.hasEntry
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import  org.hamcrest.Matchers.anything
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers.hasToString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.EnumSet.allOf
import kotlin.concurrent.thread


@RunWith(AndroidJUnit4::class)
class AddFoodUITest {

    private val searchString = "app"
    private val quantity= "1"

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityScenarioRule(AddfoodActivity::class.java)
    @Test
    fun checkInteractionWithListViewAndSearch(){
        onView(withId(R.id.search_input))
            .check(matches(
                ViewMatchers.isCompletelyDisplayed()))  //

        onView(withId(R.id.quantity_input))
            .check(matches(
                ViewMatchers.isCompletelyDisplayed()))

        onView(withId(R.id.fetch_food_button))
            .check(
                matches(isDisplayed()))

        onView(withId(R.id.search_input))
            .perform(typeText(searchString) , closeSoftKeyboard())

        onView(withId(R.id.quantity_input))
            .perform( typeText(quantity) , closeSoftKeyboard())

        onView(withId(R.id.fetch_food_button))
            .perform(click())

       onData(anything()).inAdapterView(withId(R.id.scrollView3)).atPosition(0)
           .check(matches(isDisplayed()))
           .perform(click())
    }


}