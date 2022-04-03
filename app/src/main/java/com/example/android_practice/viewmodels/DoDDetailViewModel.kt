package com.example.android_practice.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository

class DoDDetailViewModel(private val dodRepository: DoDRepository) : ViewModel() {
    class Factory(
        private val dodRepository: DoDRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DoDDetailViewModel(dodRepository) as T
        }
    }

    val dod: MutableLiveData<DoD> by lazy { MutableLiveData<DoD>() }

    init {
        load()
    }

    private fun load() {
//        dodRepository.findDoDList() {
//            this.dodList.postValue(it.items)
//        }
    }

}
