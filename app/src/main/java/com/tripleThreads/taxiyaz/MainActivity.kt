package com.tripleThreads.taxiyaz

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tripleThreads.taxiyaz.fragments.LoginFragment
import com.tripleThreads.taxiyaz.fragments.RouteFragment
import com.tripleThreads.taxiyaz.fragments.SettingsFragment
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        if (userViewModel.user == null) {
            bottom_navigation.visibility = View.INVISIBLE

            val loginFragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, loginFragment)
                .commit()
        }
        else {
            val routeFragment = RouteFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, routeFragment)
                .commit()
        }

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_recents -> {

            }
            R.id.action_settings -> {
                if(bottom_navigation.selectedItemId != R.id.action_settings){
                    val settingsFragment = SettingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, settingsFragment)
                        .commit()
                }
            }

            R.id.action_home -> {
                if(bottom_navigation.selectedItemId != R.id.action_home){
                    val routeFragment = RouteFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, routeFragment)
                        .commit()
                }
            }

            R.id.action_nearby ->{

            }
        }
        true
    }



}
