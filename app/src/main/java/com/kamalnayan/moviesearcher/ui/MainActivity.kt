package com.kamalnayan.moviesearcher.ui

import android.util.Log
import com.kamalnayan.commons.base.BaseActivity
import com.kamalnayan.moviesearcher.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun initViews() {
        Log.d("api", "initViews: main activity initiated")
    }

}