package com.tripleThreads.taxiyaz

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.RatingBar
import androidx.lifecycle.ViewModelProviders
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.tripleThreads.taxiyaz.viewModel.CommentViewModel
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

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
    fun commentTest() = runBlocking {
        activity.launchActivity(Intent())

        val commentViewModel = ViewModelProviders.of(activity.activity).get(CommentViewModel::class.java)

        onView(withId(R.id.action_home)).perform(click())

        onView(withId(R.id.search_bar)).perform(click())

        onView(withId(R.id.search_bar)).perform(typeTextIntoFocusedView("Jamo"))

        onView(withId(R.id.tabs)).perform(closeSoftKeyboard())

        onView(withId(R.id.start_route)).perform(click())

        onView(withId(R.id.editText)).perform(click())

        onView(withId(R.id.editText)).perform(clearText())

        Thread.sleep(1000)

        onView(withId(R.id.editText)).perform(typeTextIntoFocusedView("test comment"))

        onView(withId(R.id.editText)).perform(closeSoftKeyboard())

        Thread.sleep(1000)

        onView(withId(R.id.button)).perform(click())

        commentViewModel.getComments(54)
        Thread.sleep(2000)

        Log.i("fetched object", commentViewModel.comments.value.toString())

        var added = false
        for (comment in commentViewModel.commentForTest) {
            if (comment.comment == "test comment")
                added = true
        }
        assert(added)
    }


    @Test
    fun ratingTest() {
        activity.launchActivity(Intent())

        val routeViewModel = ViewModelProviders.of(activity.activity).get(RouteViewModel::class.java)

        routeViewModel.getRoute(54)

        Thread.sleep(3000)

        val before = routeViewModel.route.rating

        onView(withId(R.id.action_home)).perform(click())

        onView(withId(R.id.search_bar)).perform(click())

        onView(withId(R.id.search_bar)).perform(typeTextIntoFocusedView("Jamo"))

        onView(withId(R.id.tabs)).perform(closeSoftKeyboard())

        onView(withId(R.id.start_route)).perform(click())

        onView(withId(R.id.route_rating_bar)).perform(rate(Random.nextDouble(1.0, 5.0).toFloat()))

        Thread.sleep(1500)

        onView(withId(R.id.button)).perform(click())

        routeViewModel.getRoute(54)

        Thread.sleep(2000)

        assertNotEquals(before, routeViewModel.route.rating)
    }
}