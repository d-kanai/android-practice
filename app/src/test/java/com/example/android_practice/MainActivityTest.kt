package com.example.android_practice

import android.widget.ListView
import android.widget.TextView
import com.example.android_practice.models.DoD
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    fun loadDoDListWhenOnCreate() {
        //given
        //TODO: turn on PowerMockito
//        val pmock = PowerMockito.mock(DoDRepository::class.java)
//        `when`(pmock.findDoDList(){})
        val activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java);
        //when
        val listViewText = activity.findViewById<TextView>(R.id.tvName)
        //then
        assertEquals("Coverage", listViewText.text)
    }

    @Test
    fun moveDoDDetailPageWhenTapDoDListItem() {
        //given
//        val pmock = PowerMockito.mock(DoDRepository::class.java)
//        `when`(pmock.findDoDList(){})
        val activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java);
        //when
        val listView = activity.findViewById<ListView>(R.id.dod_list_view)
        listView.performItemClick(listView.getChildAt(0), 0, listView.getChildAt(0).id.toLong());
        //then
        val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals("com.example.android_practice.DodDetailActivity", actual.component!!.className)
        assertEquals("Coverage", (actual.extras!!["dod"] as DoD).name)
    }
}

