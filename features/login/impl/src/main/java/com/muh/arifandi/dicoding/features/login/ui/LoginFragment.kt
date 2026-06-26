package com.muh.arifandi.dicoding.features.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import android.net.Uri
import com.muh.arifandi.dicoding.features.login.databinding.FragmentLoginBinding
import com.muh.arifandi.dicoding.features.login.ui.state.LoginEffect
import com.muh.arifandi.dicoding.features.login.ui.state.LoginIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeEffect()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            viewModel.processIntent(LoginIntent.Submit)
        }
    }

    private fun observeEffect() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
