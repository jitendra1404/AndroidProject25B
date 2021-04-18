package com.example.androidproject25b

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class AppointmentInsertTest {

    @get:Rule

    val testRule= ActivityScenarioRule(AddAppointmentActivity::class.java)

    @Test
    fun testAddAppointmentUI(){
      onView(withId(R.id.etdevicename))
            .perform(typeText("dell")).perform(closeSoftKeyboard());

      onView(withId(R.id.etdevicemodel))
            .perform(typeText("dell 5559")).perform(closeSoftKeyboard());

       onView(withId(R.id.etappointmentdate))
            .perform(typeText("10-10-2010")).perform(closeSoftKeyboard());


//        Espresso.closeSoftKeyboard()

     onView(withId(R.id.etlocation))
            .perform(typeText("janakpur, Jankinagar")).perform(closeSoftKeyboard());


      onView(withId(R.id.etissue))
            .perform(typeText("battery issue")).perform(closeSoftKeyboard());


       onView(withId(R.id.btnsubmit))
            .perform(ViewActions.click())

        Thread.sleep(3000)


   onView(withId(R.id.btnsubmit))
            .check(matches(isDisplayed()))

    }
}