package com.tripleThreads.taxiyaz

import android.content.Intent
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@get:Rule
var permissionRule = GrantPermissionRule.grant(
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.ACCESS_COARSE_LOCATION
)!!
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @Mock
    var mockNavController: NavController = mock(NavController::class.java)
    private lateinit var activity: ActivityTestRule<MainActivity>

    @Before
    fun initRes() {
        mockNavController = mock(NavController::class.java)
        activity = ActivityTestRule(MainActivity::class.java)

    }

    @Test
    fun navigationToAddRouteFragment() {
        activity.launchActivity(Intent())
        onView(withId(R.id.action_add)).perform(click())

        verify(mockNavController).navigate(R.id.route_fragment_dest)

    }

    @Test
    fun navigationToRoutes() {
        activity.launchActivity(Intent())
        onView(withId(R.id.action_home)).perform(click())

        verify(mockNavController).navigate(R.id.route_fragment_dest)
    }


    @Test
    fun navigationToRecent() {
        activity.launchActivity(Intent())
        onView(withId(R.id.action_recents)).perform(click())

        verify(mockNavController).navigate(R.id.loading_fragment_dest)
    }


    @Test
    fun navigationToSettings() {
        activity.launchActivity(Intent())
        onView(withId(R.id.action_settings)).perform(click())

        verify(mockNavController).navigate(R.id.settings_fragment_dest)
    }

    @Test
    fun navigationToBookmarks() {
        activity.launchActivity(Intent())
        onView(withId(R.id.action_favorites)).perform(click())

        // to be done
    }


}