package com.example.android_practice.pages

import androidx.appcompat.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.android_practice.ViewHelper
import org.hamcrest.Matchers

object ヘッダー {
    fun 戻るナビゲーションをタップ(): ヘッダー {
        val backButton = onView(
            Matchers.allOf(
                ViewHelper.childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.action_bar),
                        ViewHelper.childAtPosition(
                            ViewMatchers.withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        backButton.perform(ViewActions.click())
        return this
    }
}