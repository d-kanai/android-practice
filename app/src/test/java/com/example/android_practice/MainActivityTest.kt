package com.example.android_practice

import android.widget.Button
import android.widget.EditText
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

    @Test
    fun createNewDoD() {
        //given
        stubDoDListApi(DoDListResponse(mutableListOf(DoD("Long Method"))))
        stubDoDCreateApi(DoD("Coverage"))
        //when
        val activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java);
        activity.findViewById<EditText>(R.id.edit_text_dod_name).setText("Coverage")
        activity.findViewById<Button>(R.id.dod_create_button).performClick()
        //then
        val dodCreateApi = postRequestedFor(urlEqualTo("/dods"))
        assertEquals(findAll(dodCreateApi)[0].bodyAsString, """{"name":"Coverage"}""")
    }

    private fun stubDoDCreateApi(dod: DoD) {
        stubFor(post("/dods").willReturn(ok().withBody(Gson().toJson(dod))))
    }

    private fun stubDoDListApi(dodListApiResponse: DoDListResponse) {
        stubFor(get("/dods").willReturn(ok().withBody(Gson().toJson(dodListApiResponse))))
    }
}

