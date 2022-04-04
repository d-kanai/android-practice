package com.example.android_practice

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.android_practice.databinding.ActivityMainBinding
import com.example.android_practice.lib.ListViewAdapter
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository
import com.example.android_practice.viewmodels.DoDListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val dodListViewModelObj: DoDListViewModel by lazy {
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
        dodListViewModelObj.navigationToDetail.observe(this@MainActivity) { dod: DoD ->
            val intent = Intent(this@MainActivity, DodDetailActivity::class.java).apply {
                putExtra("dod", dod)
            }
            startActivity(intent)

        }
    }

    private fun ActivityMainBinding.binding() {
        dodListViewModel = dodListViewModelObj
        dodListView.adapter = ListViewAdapter(applicationContext, listOf<DoD>())
    }

}
