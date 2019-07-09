package com.tripleThreads.taxiyaz

import android.content.Intent
import android.view.View
import android.widget.RatingBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentE2ETest {
    @get:Rule
    var activity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private fun rate(rating: Float): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $rating"

            override fun getConstraints() =
                CoreMatchers.allOf(ViewMatchers.isDisplayed(), ViewMatchers.isAssignableFrom(RatingBar::class.java))

            override fun perform(uiController: UiController, view: View) {
                val ratingBar = view as RatingBar
                ratingBar.rating = rating
            }
        }
    }

    @Test
    fun commentTest() {
        activity.launchActivity(Intent())

        onView(withId(R.id.start_route)).perform(click())

        onView(withId(R.id.editText)).perform(click())

        onView(withId(R.id.editText)).perform(clearText())

        onView(withId(R.id.editText)).perform(typeTextIntoFocusedView("new comment"))

        onView(withId(R.id.editText)).perform(closeSoftKeyboard())

        Thread.sleep(3500)

        onView(withId(R.id.button)).perform(click())
    }


    @Test
    fun ratingTest() {
        activity.launchActivity(Intent())

        onView(withId(R.id.start_route)).perform(click())

        onView(withId(R.id.ratingBar2)).perform(rate(2.5F))

        Thread.sleep(3500)

        onView(withId(R.id.button)).perform(click())
    }
}