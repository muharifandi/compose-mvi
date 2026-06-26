/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : app
 * File : MainActivity.kt
 */

package com.muh.arifandi.dicoding

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.muh.arifandi.dicoding.core.architecture.base.BaseActivity
import com.muh.arifandi.dicoding.core.common.security.SecurityGuard
import com.muh.arifandi.dicoding.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @Inject
    lateinit var securityGuard: SecurityGuard

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    override fun onInitViews() {
        securityGuard.checkIntegrity(BuildConfig.DEBUG) { reason ->
            Timber.e("App terminated due to: $reason")
            finish()
        }

        setSupportActionBar(binding.customToolbar.toolbar)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        // Define top-level destinations (no back button, show drawer/bottom nav)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_network,
                R.id.navigation_history,
                R.id.navigation_jobs
            ),
            binding.drawerLayout
        )

        // Setup BottomNav
        binding.customBottomNav.bottomNav.setupWithNavController(navController)
        binding.customBottomNav.bottomNav.inflateMenu(R.menu.bottom_nav_menu)
        
        // Setup Drawer
        binding.customNavView.navView.setupWithNavController(navController)
        binding.customNavView.navView.inflateMenu(R.menu.nav_drawer_menu)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home,
                R.id.navigation_network,
                R.id.navigation_history,
                R.id.navigation_jobs -> {
                    binding.customToolbar.root.visibility = View.VISIBLE
                    binding.customBottomNav.root.visibility = View.VISIBLE
                    binding.drawerLayout.setDrawerLockMode(androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED)
                }
                else -> {
                    binding.customToolbar.root.visibility = View.GONE
                    binding.customBottomNav.root.visibility = View.GONE
                    binding.drawerLayout.setDrawerLockMode(androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
