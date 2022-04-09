package com.example.android_practice.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android_practice.R

object DoD詳細ページ {
    fun DoDタイトルが表示されている(name: String): DoD詳細ページ {
        onView(ViewMatchers.withId(R.id.dod_name_text)).check(matches(withText(name)))
        return this
    }
}