package com.kamalnayan.commons.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
abstract class BaseActivity<B : ViewDataBinding>(
    private val bindingInflater: (LayoutInflater) -> B
) : AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    abstract fun initViews()

}