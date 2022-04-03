package com.example.android_practice.viewmodels

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
        DoDRepository().findDoDList() {
            this.dodList.postValue(it.items)
        }
    }

    fun getOnClickListItemListener(): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { adapterView, view, i, l -> onClickListItem() }
    }

    private fun onClickListItem() {
        println("on click trigger from xml")
        val dodList = this.dodList.value
        dodList?.add(DoD("APPEND"))
        this.dodList.value = dodList
    }

}