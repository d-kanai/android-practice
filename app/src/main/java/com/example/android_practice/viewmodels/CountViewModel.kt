package com.example.android_practice.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel : ViewModel() {
    val count: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init {
        initCount()
    }

    private fun initCount() {
        count.value = 100
    }

    fun countUp() {
        count.value = count.value!!.plus(1)
    }

}