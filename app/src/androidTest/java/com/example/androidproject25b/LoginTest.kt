package com.example.androidproject25b

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.concurrent.thread


@LargeTest
@RunWith(JUnit4::class)
class LoginTest {

    @get: Rule

    val tesRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginUI(){
        onView(withId(R.id.edUserName))
            .perform(typeText("kishan"))

        onView(withId(R.id.edloginpassword))
            .perform(typeText("kishan"))

        closeSoftKeyboard()

        onView(withId(R.id.btnSingIn))
            .perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.tablayout))
            .check(matches(isDisplayed()))
    }
}