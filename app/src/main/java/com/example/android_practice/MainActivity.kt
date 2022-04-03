package com.example.android_practice

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.dod_list_view)
        listView.setOnItemClickListener { parent, view, position, id -> onClickListItem(view) }

        val viewModel: DoDViewModel by viewModels()
        viewModel.load()
        val dodList = viewModel.findDoDList()
        val a = dodList.map { dod -> dod.name }.toList()
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, a)

        val countViewModel: CountViewModel by viewModels()
        countViewModel.countValue.observe(this,
            Observer { countValue ->
                findViewById<TextView>(R.id.count_text).text = countValue.toString()
            }
        )
        findViewById<TextView>(R.id.count_up_button).setOnClickListener { countViewModel.countUp() }
    }

    private fun onClickListItem(view: View) {
        val item = (view.findViewById<TextView>(android.R.id.text1)).text
        Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
    }


}

class DoDViewModel : ViewModel() {
    private var dodList: List<DoD> = listOf()

    fun load() {
        val dodList = DoDRepository().findDoDList()
        this.dodList = dodList
    }

    fun findDoDList(): List<DoD> {
        return dodList
    }

}

class DoD(val name: String) {

}


class CountViewModel : ViewModel() {
    var countValue = MutableLiveData(0)

    fun countUp() {
        countValue.value = countValue.value!!.plus(1)
    }

    fun getCount(): String {
        return countValue.value.toString()
    }
}