package com.example.android_practice.pages

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android_practice.R
import org.hamcrest.Matchers.anything

object DoDリストページ {

    fun DoD名を入力(name: String): DoDリストページ {
        onView(withId(R.id.edit_text_dod_name)).perform(replaceText(name), closeSoftKeyboard())
        return this
    }

    fun DoD登録フォームをサブミット(): DoDリストページ {
        onView(withText("Create")).perform(click())
        return this
    }

    fun DoDリストアイテムをタップ(): DoDリストページ {
        //TODO: how click by text not position
        onData(anything()).inAdapterView(withId(R.id.dod_list_view)).atPosition(0).perform(click());
        return this
    }


}