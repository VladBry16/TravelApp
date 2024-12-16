package com.example.travelapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.travelapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidContext(this@MainActivity)
            modules(com.example.travelapp.di.appModule)
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_flights -> {
                    navController.popBackStack(
                        R.id.menu_flights,
                        true
                    ) // Удаляем предыдущий фрагмент
                    navController.navigate(R.id.menu_flights) // Навигация к новому экземпляру
                    true
                }

                R.id.menu_search -> {
                    navController.popBackStack(R.id.menu_search, true)
                    navController.navigate(R.id.menu_search)
                    true
                }

                R.id.menu_tickets -> {
                    navController.popBackStack(R.id.menu_tickets, true)
                    navController.navigate(R.id.menu_tickets)
                    true
                }

                R.id.menu_hotels -> {
                    navController.popBackStack(R.id.menu_hotels, true)
                    navController.navigate(R.id.menu_hotels)
                    true
                }

                R.id.menu_shortly -> {
                    navController.popBackStack(R.id.menu_shortly, true)
                    navController.navigate(R.id.menu_shortly)
                    true
                }

                R.id.menu_subscriptions -> {
                    navController.popBackStack(R.id.menu_subscriptions, true)
                    navController.navigate(R.id.menu_subscriptions)
                    true
                }

                R.id.menu_profile -> {
                    navController.popBackStack(R.id.menu_profile, true)
                    navController.navigate(R.id.menu_profile)
                    true
                }

                else -> false
            }
        }
    }
}