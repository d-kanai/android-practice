package com.example.android_practice.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository

class DoDViewModel : ViewModel() {
    val dodList: MutableLiveData<List<DoD>> by lazy { MutableLiveData<List<DoD>>() }

    init {
        load()
    }

    private fun load() {
        val dodList = DoDRepository().findDoDList()
        this.dodList.value = dodList
    }

}