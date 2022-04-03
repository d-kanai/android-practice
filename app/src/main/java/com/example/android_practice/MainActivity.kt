package com.example.android_practice

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.android_practice.databinding.ActivityMainBinding
import com.example.android_practice.viewmodels.CountViewModel
import com.example.android_practice.viewmodels.DoDViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                countViewModel = CountViewModel()
                dodViewModel = DoDViewModel()
            }
    }
}

@BindingAdapter("onItemClickListener")
fun setOnItemClickListener(listView: ListView, listener: AdapterView.OnItemClickListener) {
    listView.onItemClickListener = listener
}