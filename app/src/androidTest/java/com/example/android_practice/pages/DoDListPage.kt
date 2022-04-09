package com.example.android_practice.pages

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android_practice.MainActivity
import com.example.android_practice.R
import org.hamcrest.Matchers.anything

object DoDListPage {

    fun tapListItem(name: String) {
        onData(anything()).inAdapterView(withId(R.id.dod_list_view)).atPosition(0).perform(click());
    }

    fun submitCreate() {
        onView(withText("Create")).perform(click())
    }

    fun fillInName(name: String) {
        onView(withId(R.id.edit_text_dod_name)).perform(replaceText(name), closeSoftKeyboard())
    }

    fun open() {
        val intent = MainActivity.create(InstrumentationRegistry.getInstrumentation().targetContext)
        ActivityScenario.launch<Activity>(intent)
    }

}