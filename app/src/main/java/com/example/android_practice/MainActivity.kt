package com.example.android_practice

import android.os.Bundle
import android.util.Log
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

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                countViewModel = CountViewModel()
            }

        val listView = findViewById<ListView>(R.id.dod_list_view)
        listView.setOnItemClickListener { parent, view, position, id ->
            Log.v("System", "on click listener")
            viewModel.onClickListItem(view)
            val item = (view.findViewById<TextView>(android.R.id.text1)).text
            Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
        }
        viewModel.dodList.observe(this, Observer { dodList ->
            Log.v("System", "publish dodList")
            val names = dodList!!.map { dod -> dod.name }?.toList()
            Log.v("System", names.toString())
            listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        })

    }


}


