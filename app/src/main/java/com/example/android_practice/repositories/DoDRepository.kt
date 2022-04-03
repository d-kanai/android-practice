package com.example.android_practice.repositories

import com.example.android_practice.models.DoD
import okhttp3.*
import java.io.IOException

class DoDRepository {
    fun findDoDList(): MutableList<DoD> {
        val client = OkHttpClient()
        val url: String = "http://192.168.10.109:9000/dods"
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("fail : $e")
            }

            override fun onResponse(call: Call, response: Response) {
                println(call)
                println(response.body?.string())
            }
        })
        return mutableListOf(DoD("Long Method"), DoD("Coverage"))
    }
}