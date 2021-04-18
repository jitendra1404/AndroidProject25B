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
class AddAppointmentTest {

    @get:Rule

    val testRule= ActivityScenarioRule(UpdateAppointmentActivity::class.java)

    @Test
    fun testUpdateAppointmentUI(){
       onView(withId(R.id.etDeviceName))
            .perform(typeText("dell")).perform(closeSoftKeyboard());

       onView(withId(R.id.etDeviceModel))
            .perform(typeText("dell 5559")).perform(ViewActions.closeSoftKeyboard());

      onView(withId(R.id.etAppointmentDate))
            .perform(typeText("10-10-2010")).perform(closeSoftKeyboard());


//        Espresso.closeSoftKeyboard()

     onView(withId(R.id.etLocation))
            .perform(typeText("janakpur, Jankinagar")).perform(closeSoftKeyboard());


       onView(withId(R.id.etIssue))
            .perform(typeText("battery issue")).perform(closeSoftKeyboard());


        onView(withId(R.id.btnEdit))
            .perform(ViewActions.click())

        Thread.sleep(3000)


       onView(withId(R.id.btnEdit))
            .check(matches(isDisplayed()))

    }

    }