package com.example.android_practice

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDListResponse
import com.example.android_practice.repositories.DoDRepository
import com.example.android_practice.viewmodels.DoDListViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DoDListViewModelTest {


    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.android_practice", appContext.packageName)
    }

    @Test
    fun loadInitDoDList() {
        //when
        val dodListViewModel = DoDListViewModel(FakeDoDRepository())
        //then
        assertEquals(2, dodListViewModel.dodList.value!!.size)
        assertEquals("Coverage", dodListViewModel.dodList.value!![0].name)
        assertEquals("Long Method", dodListViewModel.dodList.value!![1].name)
    }

    @Test
    fun submitNewDoD() {
        //given
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val dodRepository = FakeDoDRepository()
        val dodListViewModel = DoDListViewModel(dodRepository)
        assertEquals(2, dodListViewModel.dodList.value!!.size)
        //when
        dodListViewModel.inputDoDName.postValue("Arrow")
        dodListViewModel.onSubmitNewDoD(View(appContext))
        //then
        assertEquals("Arrow", dodRepository.createApiDoDName)
        assertEquals(3, dodListViewModel.dodList.value!!.size)
        assertEquals("Arrow", dodListViewModel.dodList.value!![2].name)
    }
}

class FakeDoDRepository : DoDRepository() {
    var createApiDoDName: String? = null

    override fun findDoDList(onSuccess: (DoDListResponse) -> Unit) {
        println("mock")
        onSuccess(
            DoDListResponse(
                items = mutableListOf(
                    DoD("Coverage"),
                    DoD("Long Method")
                )
            )
        )
    }

    override fun createDoD(name: String?, onSuccess: (dod: DoD) -> Unit) {
        createApiDoDName = name
        onSuccess(DoD(name!!))
    }

}