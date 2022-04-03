package com.example.android_practice.repositories

import com.example.android_practice.models.DoD
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class DoDRepository {
    fun findDoDList(onSuccess: (DoDListResponse) -> Unit) {
        val client = OkHttpClient()
        val url = "http://192.168.10.109:9000/dods"
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                println(json)
                val dodListResponse =
                    Gson().fromJson(json, DoDListResponse::class.java) as DoDListResponse
                onSuccess(dodListResponse)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("fail : $e")
            }
        })
    }

    fun createDoD(name: String?, onSuccess: (dod: DoD) -> Unit) {
        val client = OkHttpClient()
        val url = "http://192.168.10.109:9000/dods"
        val mediaTypeJson = "application/json".toMediaTypeOrNull()
        val params = "{\"name\":\"${name}\"}"
        println(params)
        val body = params.toRequestBody(mediaTypeJson);
        val request = Request.Builder().url(url).post(body = body).build()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                println(json)
                val dod = Gson().fromJson(json, DoD::class.java) as DoD
                onSuccess(dod)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("fail : $e")
            }
        })
    }
}

data class DoDListResponse(val items: MutableList<DoD>)
