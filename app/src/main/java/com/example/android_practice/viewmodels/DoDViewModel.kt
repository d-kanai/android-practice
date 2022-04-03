package com.example.android_practice.viewmodels

import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository

class DoDViewModel(private val dodRepository: DoDRepository) : ViewModel() {
    class Factory(
        private val dodRepository: DoDRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DoDViewModel(dodRepository) as T
        }
    }

    val dodList: MutableLiveData<MutableList<DoD>> by lazy { MutableLiveData<MutableList<DoD>>() }

    init {
        load()
    }

    private fun load() {
        println("load DoD List")
        dodRepository.findDoDList() {
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