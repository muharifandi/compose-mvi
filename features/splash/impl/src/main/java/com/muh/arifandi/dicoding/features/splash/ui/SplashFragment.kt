package com.muh.arifandi.dicoding.features.splash.ui

import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.muh.arifandi.dicoding.core.architecture.base.BaseFragment
import com.muh.arifandi.dicoding.features.splash.databinding.FragmentSplashBinding
import com.muh.arifandi.dicoding.features.splash.ui.state.SplashEffect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onInitViews() {
        // No views to initialize
    }

    override fun onInitObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is SplashEffect.NavigateToLogin -> {
                            findNavController().navigate(Uri.parse("saka://login"))
                        }
                        is SplashEffect.NavigateToHome -> {
                            findNavController().navigate(Uri.parse("saka://home"))
                        }
                        is SplashEffect.NavigateToIntro -> {
                            findNavController().navigate(Uri.parse("saka://intro"))
                        }
                    }
                }
            }
        }
    }
}
