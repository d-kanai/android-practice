package com.example.android_practice

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.android_practice.repositories.DoDRepository

class MainActivity : AppCompatActivity() {

    private val viewModel: DoDViewModel by viewModels()
    private val countViewModel: CountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.dod_list_view)
        listView.setOnItemClickListener { parent, view, position, id -> onClickListItem(view) }

        viewModel.dodList.observe(this, Observer { dodList ->
            Log.v("APP_LOG", "publish dodList")
            val a = dodList!!.map { dod -> dod.name }?.toList()
            listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, a)
        })

        countViewModel.count.observe(this, Observer { count ->
            Log.v("APP_LOG", "publish count")
            findViewById<TextView>(R.id.count_text).text = count.toString()
        })
        findViewById<TextView>(R.id.count_up_button).setOnClickListener { countViewModel.countUp() }
    }

    private fun onClickListItem(view: View) {
        val item = (view.findViewById<TextView>(android.R.id.text1)).text
        Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
    }


}

class DoDViewModel : ViewModel() {
    val dodList: MutableLiveData<List<DoD>> by lazy { MutableLiveData<List<DoD>>() }

    init {
        load()
    }

    private fun load() {
        val dodList = DoDRepository().findDoDList()
        this.dodList.value = dodList
    }

}

class DoD(val name: String) {

}


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