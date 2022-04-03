package com.example.android_practice.repositories

import com.example.android_practice.models.DoD
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class DoDRepository {
    fun findDoDList(callBackFetchDoDList: (DoDListResponse) -> Unit) {
        val client = OkHttpClient()
        val url = "http://192.168.10.109:9000/dods"
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                println(json)
                val dodListResponse =
                    Gson().fromJson(json, DoDListResponse::class.java) as DoDListResponse
                callBackFetchDoDList(dodListResponse)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("fail : $e")
            }
        })
    }
}

data class DoDListResponse(val items: MutableList<DoD>)
