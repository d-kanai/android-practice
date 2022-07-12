package com.example.android_practice

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android_practice.databinding.ActivityDodDetailBinding
import com.example.android_practice.models.DoD
import com.example.android_practice.repositories.DoDRepository
import com.example.android_practice.viewmodels.DoDDetailViewModel

class DodDetailActivity : AppCompatActivity() {

    private val dodDetailViewModel: DoDDetailViewModel by lazy {
        val factory = DoDDetailViewModel.Factory(DoDRepository())
        ViewModelProvider(this, factory)[DoDDetailViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dod_detail)
        val dodParam = intent.getSerializableExtra("dod") as DoD
        DataBindingUtil.setContentView<ActivityDodDetailBinding>(this, R.layout.activity_dod_detail)
            .apply {
                lifecycleOwner = this@DodDetailActivity
                viewModel = dodDetailViewModel
                dod = dodParam
            }
    }
}

