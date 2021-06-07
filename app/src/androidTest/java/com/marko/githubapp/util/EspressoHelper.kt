package com.marko.githubapp.util

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.containsString

/**
 * Helper file that contains methods for easier and cleaner way of writing ui tests.
 */

const val FIRST_POSITION = 0

/**
 * Sleeps for 500ms
 */
fun sleepShort() = SystemClock.sleep(500)

/**
 * Checks if given view is visible
 *
 * @param id view id
 */
fun isViewVisible(id: Int): ViewInteraction = onView(withId(id)).check(matches(isDisplayed()))

/**
 * Checks if given text matches with text on view
 *
 * @param viewId view id
 * @param text text that needs to checked
 */
fun checkTextOnView(viewId: Int, text: String): ViewInteraction =
    onView(withId(viewId)).check(matches(withText(text)))

/**
 * Checks if text is visible on screen
 *
 * @param resourceId string id of text
 */
fun isTextVisible(resourceId: Int): ViewInteraction =
    onView(withText(resourceId)).check(matches(isDisplayed()))

/**
 * Clicks on given view
 *
 * @param id view id
 */
fun clickOnView(id: Int): ViewInteraction = onView(withId(id)).perform(click())

/**
 * Clicks on view that is inside recycler view
 *
 * @param recyclerViewId id of recycler view
 * @param recyclerViewItemId id of item that needs to be clicked inside recycler view
 * @param position position of item in recycler view
 */
fun clickOnRecyclerViewItem(
    recyclerViewId: Int, recyclerViewItemId: Int, position: Int
): ViewInteraction = onView(
    withRecyclerView(recyclerViewId).atPositionOnView(position, recyclerViewItemId))
    .perform(click())

/**
 * Checks text on view that is inside recycler view
 *
 * @param recViewId id of recycler view
 * @param itemId id of view that text needs to be checked on
 * @param position position of item in recycler view
 * @param text text that needs to be checked
 */
fun checkTextOnRecyclerViewItem(
    recViewId: Int,
    itemId: Int,
    position: Int,
    text: String
): ViewInteraction =
    onView(withRecyclerView(recViewId).atPositionOnView(position, itemId))
        .check(matches(withText(containsString(text))))

/**
 * Checks number of items inside the recycler view
 *
 * @param viewId id of recycler view
 * @param expectedCount number of expected items
 */
fun checkRecyclerViewItemCount(viewId: Int, expectedCount: Int): ViewInteraction =
    onView(withId(viewId)).check(RecyclerViewItemCountAssertion(expectedCount))


/**
 * Returns RecyclerViewMatcher with provided id
 *
 * @recyclerViewId recycler resource id
 */
fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
}

