package com.tripleThreads.taxiyaz

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tripleThreads.taxiyaz.fragments.RouteFragment
import com.tripleThreads.taxiyaz.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // main_frame -- root fragment holder
    // fragment_route -- root fragment for routing [BestRouteFragment, AlternativeRoutingFragment]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val routeFragment = RouteFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, routeFragment)
            .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_recents -> {

            }
            R.id.action_settings -> {
                val settingsFragment = SettingsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, settingsFragment)
                    .commit()

            }
            R.id.action_home -> {
                val routeFragment = RouteFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, routeFragment)
                    .commit()
            }
        }
        true
    }
}
