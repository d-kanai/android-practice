package com.example.android_practice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_practice.databinding.ActivityMainBinding
import com.example.android_practice.lib.ListViewAdapter
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository
import com.example.android_practice.viewmodels.DoDListViewModel


class MainActivity : AppCompatActivity() {

    private val dodListViewModelObj: DoDListViewModel by lazy {
        val factory = DoDListViewModel.Factory(DoDRepository())
        ViewModelProvider(this, factory)[DoDListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                binding()
                observe()
            }

    }

    private fun ActivityMainBinding.observe() {
        dodListViewModelObj.dodList.observe(this@MainActivity, Observer { dodList ->
            (dodListView.adapter as ListViewAdapter<DoD>).updateItems(dodList)
        })
        dodListViewModelObj.navigation.observe(this@MainActivity) { navigation ->
            startActivity(Intent(this@MainActivity, DodDetailActivity::class.java))
        }
    }

    private fun ActivityMainBinding.binding() {
        dodListViewModel = dodListViewModelObj
        dodListView.adapter = ListViewAdapter(applicationContext, listOf<DoD>())
    }

}
