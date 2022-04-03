package com.example.android_practice.repositories

import com.example.android_practice.models.DoD

class DoDRepository {
    fun findDoDList(): MutableList<DoD> {
        return mutableListOf(DoD("Long Method"), DoD("Coverage"))
    }
}