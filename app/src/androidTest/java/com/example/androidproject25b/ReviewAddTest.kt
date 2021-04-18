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
import com.example.androidproject25b.Fragments.ReviewActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class ReviewAddTest {

    @get:Rule

    val testRule= ActivityScenarioRule(AddReviewActivity::class.java)

    @Test
    fun testAddReviewUI(){
        onView(withId(R.id.etCustomerName))
            .perform(typeText("Kishan Sah")).perform(closeSoftKeyboard());

       onView(withId(R.id.etFeedbackTitle))
            .perform(typeText("battery issue service")).perform(closeSoftKeyboard());

       onView(withId(R.id.etFeedbackDescription))
            .perform(typeText("technicians are provide really impressive repair without any trouble")).perform(closeSoftKeyboard());


       onView(withId(R.id.btnAddReview))
            .perform(ViewActions.click())

        Thread.sleep(3000)


       onView(withId(R.id.btnAddReview))
            .check(matches(isDisplayed()))

    }

    }
