package com.tripleThreads.taxiyaz

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginE2ETest {

    @get:Rule
    var activity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun login() {
        activity.launchActivity(Intent())

        onView(withId(R.id.user_name)).perform(click()).perform(clearText())
        onView(withId(R.id.user_name)).perform(typeTextIntoFocusedView("name"), closeSoftKeyboard())
        onView(withId(R.id.phone_number)).perform(click())
        onView(withId(R.id.phone_number)).perform(clearText())
        onView(withId(R.id.phone_number)).perform(typeTextIntoFocusedView("2519122222"))
        onView(withId(R.id.phone_number)).perform(closeSoftKeyboard())

        Thread.sleep(1000)
        onView(withId(R.id.register_button)).perform(click())

    }


}