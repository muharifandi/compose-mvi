package com.muh.arifandi.dicoding.features.about

import app.cash.turbine.test
import com.muh.arifandi.dicoding.features.about.state.AboutEffect
import com.muh.arifandi.dicoding.features.about.state.AboutIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AboutViewModelTest {

    private lateinit var viewModel: AboutViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AboutViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when Back intent is processed, should send NavigateBack effect`() = runTest {
        viewModel.effect.test {
            viewModel.processIntent(AboutIntent.Back)
            val effect = awaitItem()
            assert(effect is AboutEffect.NavigateBack)
        }
    }

    @Test
    fun `initial state should contain correct developer info`() = runTest {
        viewModel.state.test {
            val state = awaitItem()
            assertEquals("Muh. Arifandi", state.name)
            assertEquals("arif76440@gmail.com", state.email)
        }
    }
}
