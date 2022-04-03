package com.example.android_practice.repositories

import com.example.android_practice.models.DoD

class DoDRepository {
    fun findDoDList(): List<DoD> {
        return listOf(DoD("Long Method"), DoD("Coverage"))
    }
}