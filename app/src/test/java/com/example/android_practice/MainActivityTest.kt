package com.example.android_practice

import android.widget.ListView
import android.widget.TextView
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDListResponse
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Rule
    @JvmField
    var wireMockRule = WireMockRule(9000)

    @Test
    fun loadDoDListWhenOnCreate() {
        //given
        stubDoDListApi(DoDListResponse(mutableListOf(DoD("Long Method"))))
        //when
        val activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java);
        val listViewText = activity.findViewById<TextView>(R.id.tvName)
        //then
        assertEquals("Long Method", listViewText.text)
    }

    @Test
    fun moveDoDDetailPageWhenTapDoDListItem() {
        //given
        stubDoDListApi(DoDListResponse(mutableListOf(DoD("Long Method"))))
        //when
        val activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java);
        val listView = activity.findViewById<ListView>(R.id.dod_list_view)
        listView.performItemClick(listView.getChildAt(0), 0, listView.getChildAt(0).id.toLong());
        //then
        val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals("com.example.android_practice.DodDetailActivity", actual.component!!.className)
        assertEquals("Long Method", (actual.extras!!["dod"] as DoD).name)
    }

    private fun stubDoDListApi(dodListApiResponse: DoDListResponse) {
        stubFor(get("/dods").willReturn(ok().withBody(Gson().toJson(dodListApiResponse))))
    }
}

