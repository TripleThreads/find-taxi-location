package com.tripleThreads.taxiyaz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.google.android.material.tabs.TabLayout
import com.tripleThreads.taxiyaz.fragments.add_route.BottomDialogFragment
import com.tripleThreads.taxiyaz.fragments.add_route.LATITUDE
import com.tripleThreads.taxiyaz.fragments.add_route.LONGITUDE
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

@get:Rule
var permission = GrantPermissionRule.grant(
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.ACCESS_COARSE_LOCATION
)!!
@RunWith(AndroidJUnit4::class)
class RouteE2ETest {

    @get:Rule
    var activity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }

    @Test
    fun searchRoute() {
        activity.launchActivity(Intent())

        onView(withId(R.id.action_home)).perform(click())

        onView(withId(R.id.search_bar)).perform(click())

        onView(withId(R.id.search_bar)).perform(typeTextIntoFocusedView("Jamo"))

        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))

        onView(withId(R.id.tabs)).perform(closeSoftKeyboard())

        onView(withId(R.id.alternate_route_recycler_view)).
            perform(RecyclerViewActions.actionOnItemAtPosition<RouteViewHolder>(0, click()))

    }

    @Test
    fun createNode() {
        activity.launchActivity(Intent())

        onView(withId(R.id.action_add)).perform(click())

        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        onView(withId(R.id.floatingActionButton)).perform(click())

        onView(withId(R.id.location_name)).perform(click())
        onView(withId(R.id.location_name)).perform(typeTextIntoFocusedView("Megenagna"))
        onView(withId(R.id.location_name)).perform(closeSoftKeyboard())

        onView(withId(R.id.radio_pinned)).perform(click())
        onView(withId(R.id.radio_current)).perform(click())

        onView(withId(R.id.submit_node)).perform(click())

    }

}