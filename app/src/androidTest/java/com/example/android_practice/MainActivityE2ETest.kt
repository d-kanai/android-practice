package com.example.android_practice


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityE2ETest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    object MainPage {
        fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int
        ): Matcher<View> {

            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description) {
                    description.appendText("Child at position $position in parent ")
                    parentMatcher.describeTo(description)
                }

                public override fun matchesSafely(view: View): Boolean {
                    val parent = view.parent
                    return parent is ViewGroup && parentMatcher.matches(parent)
                            && view == parent.getChildAt(position)
                }
            }
        }

        fun fillInNewDoD(name: String): MainPage {
            val appCompatEditText = onView(
                allOf(
                    withId(R.id.edit_text_dod_name),
                    childAtPosition(
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        ),
                        2
                    ),
                    isDisplayed()
                )
            )
            appCompatEditText.perform(replaceText(name), closeSoftKeyboard())
            return this
        }

        fun submitNewDoDButton(): MainPage {
            val materialButton = onView(
                allOf(
                    withId(R.id.dod_create_button), withText("Create"),
                    childAtPosition(
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        ),
                        3
                    ),
                    isDisplayed()
                )
            )
            materialButton.perform(click())
            return this
        }

        fun clickDoDListItem(): MainPage {
            val linearLayout = onData(anything())
                .inAdapterView(
                    allOf(
                        withId(R.id.dod_list_view),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    )
                )
                .atPosition(0)
            linearLayout.perform(click())
            return this
        }

        fun assertShow(name: String): MainPage {
            onView(withId(R.id.dod_name_text)).check(matches(withText(name)))
            return this
        }

        fun clickNavBackButton(): MainPage {
            val appCompatImageButton = onView(
                allOf(
                    withContentDescription("Navigate up"),
                    childAtPosition(
                        allOf(
                            withId(androidx.appcompat.R.id.action_bar),
                            childAtPosition(
                                withId(androidx.appcompat.R.id.action_bar_container),
                                0
                            )
                        ),
                        1
                    ),
                    isDisplayed()
                )
            )
            appCompatImageButton.perform(click())
            return this
        }
    }

    fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }


    @Test
    fun createDoDAndShow() {
        MainPage
            .fillInNewDoD("Coverage")
            .submitNewDoDButton()
            .clickDoDListItem()
            .assertShow("Coverage")
            .clickNavBackButton()
    }
}
