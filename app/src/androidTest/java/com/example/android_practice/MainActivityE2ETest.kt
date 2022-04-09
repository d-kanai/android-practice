package com.example.android_practice

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.android_practice.pages.DoDリストページ
import com.example.android_practice.pages.DoD詳細ページ
import com.example.android_practice.pages.ヘッダー
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityE2ETest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun `DoDを登録し表示される`() {
        DoDリストページ
            .DoD名を入力("Test")
            .DoD登録フォームをサブミット()
            .DoDリストアイテムをタップ()
        DoD詳細ページ
            .DoDタイトルが表示されている("Test")
        ヘッダー
            .戻るナビゲーションをタップ()
    }
}
