package com.example.android_practice.steps

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android_practice.MainActivity
import com.example.android_practice.R
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.hamcrest.Matchers.anything


class DoDSteps {

    init {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val intent = MainActivity.create(instrumentation.targetContext)
        ActivityScenario.launch<Activity>(intent)
    }

    @Given("DoDフォームに{string}を入力")
    fun DoDフォームにsを入力(name: String) {
        onView(withId(R.id.edit_text_dod_name)).perform(replaceText(name), closeSoftKeyboard())
    }

    @When("DoD登録フォームをサブミット")
    fun DoD登録フォームをサブミット() {
        onView(withText("Create")).perform(click())
    }

    @And("DoDリストの{string}をタップ")
    fun DoDリストのsをタップ(name: String) {
        onData(anything()).inAdapterView(withId(R.id.dod_list_view)).atPosition(0).perform(click());
    }

    @Then("DoD詳細に{string}が表示される")
    fun DoD詳細にsが表示される(name: String) {
        onView(withId(R.id.dod_name_text)).check(matches(withText("Test")))
    }

}