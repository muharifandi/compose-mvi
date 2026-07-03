package com.muh.arifandi.dicoding.features.login.ui

import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.muh.arifandi.dicoding.core.architecture.base.BaseFragment
import com.muh.arifandi.dicoding.features.login.databinding.FragmentLoginBinding
import com.muh.arifandi.dicoding.features.login.ui.state.LoginEffect
import com.muh.arifandi.dicoding.features.login.ui.state.LoginIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onInitViews() {
        binding.btnLogin.setOnClickListener {
            viewModel.processIntent(LoginIntent.Submit)
        }
    }

    override fun onInitObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is LoginEffect.NavigateToHome -> {
                            findNavController().navigate(Uri.parse("saka://home"))
                        }
                        is LoginEffect.NavigateToRegister -> {
                            // findNavController().navigate(Uri.parse("saka://register"))
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}
