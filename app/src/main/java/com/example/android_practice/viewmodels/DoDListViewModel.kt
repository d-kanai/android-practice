package com.example.android_practice.viewmodels

import android.view.View
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
    var inputDoDName = MutableLiveData<String>()
    val navigationToDetail: MutableLiveData<DoD> = MutableLiveData<DoD>()

    init {
        load()
    }

    fun load() {
        dodRepository.findDoDList() {
            this.dodList.postValue(it.items)
        }
    }

    fun onSubmitNewDoD(view: View) {
        println("on submit New DoD")
        println(inputDoDName.value)
        dodRepository.createDoD(inputDoDName.value) {
            this.dodList.value?.add(it)
            this.dodList.postValue(this.dodList.value)
        }
    }

    fun getOnClickListItemListener(): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { parent, view, position, id ->
            println("on click list item")
            navigationToDetail.value = parent.getItemAtPosition(position) as DoD
        }
    }

}

sealed class Page {
    object DoDDetail : Page()
}