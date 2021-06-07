package com.marko.githubapp.util

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.containsString

const val FIRST_POSITION = 0

fun sleepShort() = SystemClock.sleep(500)

fun isViewVisible(id: Int): ViewInteraction = onView(withId(id)).check(matches(isDisplayed()))

fun checkTextOnView(viewId: Int, text: String): ViewInteraction =
    onView(withId(viewId)).check(matches(withText(text)))

fun isTextVisible(resourceId: Int): ViewInteraction =
    onView(withText(resourceId)).check(matches(isDisplayed()))

fun clickOnView(id: Int): ViewInteraction = onView(withId(id)).perform(click())

fun clickOnRecyclerViewItem(
    recyclerViewId: Int, recyclerViewItemId: Int, position: Int
): ViewInteraction = onView(
    withRecyclerView(recyclerViewId).atPositionOnView(position, recyclerViewItemId))
    .perform(click())

fun checkTextOnRecyclerViewItem(
    recViewId: Int,
    itemId: Int,
    position: Int,
    text: String
): ViewInteraction =
    onView(withRecyclerView(recViewId).atPositionOnView(position, itemId))
        .check(matches(withText(containsString(text))))


fun isViewVisibleInRecyclerView(recViewId: Int, itemId: Int, position: Int): ViewInteraction =
    onView(withRecyclerView(recViewId).atPositionOnView(position, itemId))
        .check(matches(isDisplayed()))


fun checkRecyclerViewItemCount(viewId: Int, expectedCount: Int): ViewInteraction =
    onView(withId(viewId)).check(RecyclerViewItemCountAssertion(expectedCount))


/**
 * Returns RecyclerViewMatcher with provided id
 * @recyclerViewId recycler resource id
 */
fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
}

