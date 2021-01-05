package com.ys.bottomnavigationsample

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ys.bottomnavigationsample.ui.dashboard.DashboardFragment
import com.ys.bottomnavigationsample.ui.home.HomeFragment
import com.ys.bottomnavigationsample.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val dashboardFragment = DashboardFragment()
    private val notificationsFragment = NotificationsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        findViewById<BottomNavigationView>(R.id.nav_view).setOnNavigationItemSelectedListener(bottomNavigationListener)

        changeFragment(homeFragment)

    }

    private val bottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard -> {
                changeFragment(dashboardFragment)
            }

            R.id.navigation_notifications -> {
                changeFragment(notificationsFragment)
            }

            else -> {
                changeFragment(homeFragment)
            }
        }

        true
    }

    private fun hideFragments(fragments: List<Fragment>, fragmentTransaction: FragmentTransaction) {
        for (fragment in fragments) {
            fragment.also {
                if (it.isAdded) fragmentTransaction.hide(it)
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        try {
            val ft = supportFragmentManager.beginTransaction()

            fragment.also {
                if (it.isAdded) { // if the fragment is already in container
                    ft.show(it)
                } else { // fragment needs to be added to frame container
                    ft.add(R.id.container_view, it)
                }
            }

            when (fragment) {
                is HomeFragment -> {
                    hideFragments(listOf(dashboardFragment, notificationsFragment), ft)
                }

                is DashboardFragment -> {
                    hideFragments(listOf(homeFragment, notificationsFragment), ft)
                }

                is NotificationsFragment -> {
                    hideFragments(listOf(homeFragment, dashboardFragment), ft)
                }

                else -> return
            }

            ft.commitAllowingStateLoss()
        } catch (e: Exception) {
        }
    }

}