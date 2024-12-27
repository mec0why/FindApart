package com.wsb.findapart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.AppBarLayout
import com.wsb.findapart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = binding.navView
        val toolbar = binding.toolbar
        val collapsingToolbar = binding.collapsingToolbar

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home)
        )
        NavigationUI.setupWithNavController(bottomNav, navController)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            collapsingToolbar.title = destination.label

            if (destination.id == R.id.navigation_home) {
                binding.collapsingToolbar.layoutParams = (binding.collapsingToolbar.layoutParams as AppBarLayout.LayoutParams).apply {
                    scrollFlags = 0
                }
                binding.headerImage.visibility = View.VISIBLE
            } else {
                binding.collapsingToolbar.layoutParams = (binding.collapsingToolbar.layoutParams as AppBarLayout.LayoutParams).apply {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }
                binding.headerImage.visibility = View.GONE
            }
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.help_screen -> {
                    navController.navigate(R.id.navigation_help)
                    true
                }
                else -> false
            }
        }
    }
}