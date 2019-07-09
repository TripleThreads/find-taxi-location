package com.tripleThreads.taxiyaz

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
import org.junit.Assert
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


        val userViewModel = ViewModelProviders.of(activity.activity).get(UserViewModel::class.java)

        userViewModel.getUser()
        Thread.sleep(1000)
        userViewModel.delete(userViewModel.user!!)
        activity.finishActivity()
        activity.launchActivity(Intent())

        onView(withId(R.id.user_name)).perform(click()).perform(clearText())
        Thread.sleep(1000)
        onView(withId(R.id.user_name)).perform(typeTextIntoFocusedView("Yeabsira Tesfaye"), closeSoftKeyboard())
        onView(withId(R.id.phone_number)).perform(click())
        onView(withId(R.id.phone_number)).perform(clearText())
        Thread.sleep(1000)
        onView(withId(R.id.phone_number)).perform(typeTextIntoFocusedView("0922343241"))
        onView(withId(R.id.phone_number)).perform(closeSoftKeyboard())

        Thread.sleep(1000)
        onView(withId(R.id.register_button)).perform(click())

        onView(withId(R.id.search_bar)).check(matches(isDisplayed()))
    }


}