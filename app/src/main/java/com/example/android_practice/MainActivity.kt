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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android_practice.databinding.ActivityMainBinding
import com.example.android_practice.viewmodels.CountViewModel
import com.example.android_practice.viewmodels.DoDViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: DoDViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.dod_list_view)
        listView.setOnItemClickListener { parent, view, position, id -> onClickListItem(view) }
        viewModel.dodList.observe(this, Observer { dodList ->
            Log.v("APP_LOG", "publish dodList")
            val names = dodList!!.map { dod -> dod.name }?.toList()
            listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        })

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                countViewModel = CountViewModel()
            }
    }

    private fun onClickListItem(view: View) {
        val item = (view.findViewById<TextView>(android.R.id.text1)).text
        Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
    }


}


