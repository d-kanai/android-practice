package com.example.android_practice.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android_practice.R

object DoDDetailPage {
    fun title(): ViewInteraction {
        return onView(withId(R.id.dod_name_text))
    }
}