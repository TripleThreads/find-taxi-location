package com.tripleThreads.taxiyaz

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.tripleThreads.taxiyaz.fragments.add_route.AddTaxiToNodeFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddNewTaxiUITest {

    @Test
    fun test() {
        launchFragmentInContainer<AddTaxiToNodeFragment>(Bundle(), R.style.AppTheme)

        onView(withId(R.id.taxi_location)).perform(click())
        onView(withId(R.id.taxi_location)).perform(typeTextIntoFocusedView("Shola"))

        onView(withId(R.id.taxi_location)).perform(closeSoftKeyboard())
    }
}