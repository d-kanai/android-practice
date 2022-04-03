package com.example.android_practice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_practice.databinding.ActivityMainBinding
import com.example.android_practice.lib.ListViewAdapter
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository
import com.example.android_practice.viewmodels.CountViewModel
import com.example.android_practice.viewmodels.DoDViewModel


class MainActivity : AppCompatActivity() {

    private val dodViewModelObj: DoDViewModel by lazy {
        val factory = DoDViewModel.Factory(DoDRepository())
        ViewModelProvider(this, factory)[DoDViewModel::class.java]
    }
    private val countViewModelObj: CountViewModel by lazy {
        ViewModelProvider(this).get(CountViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                countViewModel = countViewModelObj
                dodViewModel = dodViewModelObj
                dodListView.adapter = ListViewAdapter(applicationContext, listOf<DoD>())
                (dodViewModel as DoDViewModel).dodList.observe(this@MainActivity, Observer { dodList ->
                    (dodListView.adapter as ListViewAdapter<DoD>).updateItems(dodList)
                })
            }

    }
}
