package com.muh.arifandi.dicoding.core.architecture.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
        onInitViews()
        onInitObservers()
    }

    abstract fun onInitViews()
    open fun onInitObservers() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
