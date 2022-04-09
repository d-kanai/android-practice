package com.example.android_practice.steps

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android_practice.pages.DoDDetailPage
import com.example.android_practice.pages.DoDListPage
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When


class DoDSteps {

    @Given("DoDリストページを開く")
    fun DoDリストページを開く() {
        DoDListPage.open()
    }

    @Given("DoDフォームに{string}を入力")
    fun DoDフォームにsを入力(name: String) {
        DoDListPage.fillInName(name)
    }

    @When("DoD登録フォームをサブミット")
    fun DoD登録フォームをサブミット() {
        DoDListPage.submitCreate()
    }

    @When("DoDリストの{string}をタップ")
    fun DoDリストのsをタップ(name: String) {
        DoDListPage.tapListItem(name)
    }

    @Then("DoD詳細に{string}が表示される")
    fun DoD詳細にsが表示される(name: String) {
        DoDDetailPage.title().check(matches(withText("Test")))
    }

}


