package com.example.android_practice.viewmodels

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository

class DoDViewModel : ViewModel() {
    val dodList: MutableLiveData<MutableList<DoD>> by lazy { MutableLiveData<MutableList<DoD>>() }

    init {
        load()
    }

    private fun load() {
        println("load DoD List")
        this.dodList.value = DoDRepository().findDoDList()
    }

    fun getOnItemClickListener(): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { adapterView, view, i, l ->
            println("on click trigger from xml")
            val dodList = this.dodList.value
            dodList?.add(DoD("APPEND"))
            this.dodList.value = dodList
        }
    }

    fun onClickListItem(view: View?) {
        println("onClick DoD List Item")
        val dodList = this.dodList.value
        dodList?.add(DoD("APPEND"))
        this.dodList.value = dodList
    }

}