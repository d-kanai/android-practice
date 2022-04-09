package com.example.android_practice.pages

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.android_practice.R
import org.hamcrest.Matchers

object DoDリストページ {

    fun DoD名を入力(name: String): DoDリストページ {
        onView(ViewMatchers.withId(R.id.edit_text_dod_name))
            .perform(ViewActions.replaceText(name), ViewActions.closeSoftKeyboard())
        return this
    }

    fun DoD登録フォームをサブミット(): DoDリストページ {
        onView(ViewMatchers.withText("Create")).perform(ViewActions.click())
        return this
    }

    fun DoDリストアイテムをタップ(): DoDリストページ {
        //TODO: how click by text not position
        onData(Matchers.anything()).inAdapterView(ViewMatchers.withId(R.id.dod_list_view))
            .atPosition(0)
            .perform(ViewActions.click());
        return this
    }


}