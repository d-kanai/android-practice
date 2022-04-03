package com.example.android_practice.viewmodels

import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository

class DoDListViewModel(private val dodRepository: DoDRepository) : ViewModel() {
    class Factory(
        private val dodRepository: DoDRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DoDListViewModel(dodRepository) as T
        }
    }

    val dodList: MutableLiveData<MutableList<DoD>> by lazy { MutableLiveData<MutableList<DoD>>() }
    val navigation: MutableLiveData<Page> = MutableLiveData<Page>()

    init {
        load()
    }

    private fun load() {
        dodRepository.findDoDList() {
            this.dodList.postValue(it.items)
        }
    }

    fun getOnClickListItemListener(): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { adapterView, view, i, l ->
            println("on click list item")
            navigation.value = Page.DoDDetail
        }
    }

}

sealed class Page {
    object DoDDetail : Page()
}