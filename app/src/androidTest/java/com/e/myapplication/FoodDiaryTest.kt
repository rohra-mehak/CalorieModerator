package com.e.myapplication

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.BundleMatchers.hasEntry
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Before
import org.junit.Rule
import java.util.EnumSet.allOf
import kotlin.concurrent.thread
import org.junit.After
import org.junit.Assert.*



import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FoodDiaryTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityScenarioRule(FooddiaryActivity::class.java)


    @Test
    fun checkForAddButtonPressedMatchScreen() {
        Intents.init()
        onView(withId(R.id.button_addfood))
            .check(matches(isDisplayed()))   //assert view  if button is  displayed
            .perform(click())
        intended(hasComponent(AddfoodActivity::class.java.name))
        Intents.release()

        //check if  intent  was started

    }
    @Test
    fun checkPieChartDisplay (){
        onView(withId(R.id.pie_chart))
            .check(matches(isDisplayed()))   //assert view  is  displayed
            .perform(click())

    }
    @Test
    fun checkForIntentStarts(){

        Intents.init()
        onView(withId(R.id.bottom_profile))
            .check(matches(isDisplayed()))              //assert view  if button is  displayed
            .perform(click())
        intended(hasComponent(
            EnterweightActivity::class.java.name
        ))                                               //assert  right intent was started
        Intents.release()


        Intents.init()
        onView(withId(R.id.bottom_food))
            .check(matches(isDisplayed()))              //assert view  if button is  displayed
            .perform(click())
        intended(hasComponent(FooddiaryActivity::class.java.getName()))
        Intents.release()


        Intents.init()
        onView(withId(R.id.third_button))
            .check(matches(isDisplayed()))                        //assert view  if button is  displayed
            .perform(click())
        intended(
            hasComponent(ReportsActivity::class.java.getName()))                   //check if intent was started
        Intents.release()

        Intents.init()
        onView(withId(R.id.bottom_test))
            .check(matches(isDisplayed()))                              //assert view  if button is  displayed
            .perform(click())
        intended(hasComponent(ProfileActivity::class.java.getName()))
        Intents.release()



    }
}


