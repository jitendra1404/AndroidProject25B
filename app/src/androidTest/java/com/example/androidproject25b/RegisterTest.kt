package com.example.androidproject25b



import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class RegisterTest {

    @get:Rule

    val testRule= ActivityScenarioRule(RegistrationActivity::class.java)

    @Test
    fun testRegisterUI(){
        onView(withId(R.id.edRegisterUsername))
            .perform(typeText("kishan")).perform(closeSoftKeyboard());


        onView(withId(R.id.edEmail))
            .perform(typeText("kishan@123")).perform(closeSoftKeyboard());


        onView(withId(R.id.edAddress))
            .perform(typeText("ktm")).perform(closeSoftKeyboard());


//        Espresso.closeSoftKeyboard()

        onView(withId(R.id.edmobile))
            .perform(typeText("9807269393")).perform(closeSoftKeyboard());


        onView(withId(R.id.edPassword))
            .perform(typeText("kishan")).perform(closeSoftKeyboard());


        onView(withId(R.id.edConfirmPassword))
            .perform(typeText("kishan")).perform(closeSoftKeyboard());


        onView(withId(R.id.btnSingUP))
            .perform(click())

        Thread.sleep(3000)


        onView(withId(R.id.btnSingUP))
            .check(matches(isDisplayed()))

    }

}