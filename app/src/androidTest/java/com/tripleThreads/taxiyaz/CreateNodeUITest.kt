package com.tripleThreads.taxiyaz

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.tripleThreads.taxiyaz.fragments.add_route.BottomDialogFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateNodeUITest {

    @Test
    fun startCreateNode() {
        launchFragmentInContainer<BottomDialogFragment>(Bundle(), R.style.AppTheme)

        onView(withId(R.id.location_name)).perform(click())
        onView(withId(R.id.location_name)).perform(typeTextIntoFocusedView("Megenagna"))
        onView(withId(R.id.location_name)).perform(closeSoftKeyboard())

        onView(withId(R.id.radio_pinned)).perform(click())
        onView(withId(R.id.radio_current)).perform(click())

        onView(withId(R.id.submit_node)).perform(click())
    }

}