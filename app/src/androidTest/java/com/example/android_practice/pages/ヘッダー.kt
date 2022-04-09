package com.example.android_practice.pages

import androidx.appcompat.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android_practice.ViewHelper.childAtPosition
import org.hamcrest.Matchers.allOf

object ヘッダー {

    fun 戻るナビゲーションをタップ(): ヘッダー {
        val backButton = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(withId(R.id.action_bar_container), 0)
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        backButton.perform(ViewActions.click())
        return this
    }

}