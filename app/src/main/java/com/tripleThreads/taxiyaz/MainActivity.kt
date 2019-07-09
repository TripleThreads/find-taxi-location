package com.tripleThreads.taxiyaz

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.databinding.ActivityMainBinding
import com.tripleThreads.taxiyaz.viewModel.LocationViewModel
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
import kotlinx.coroutines.GlobalScope
import org.jetbrains.anko.doAsync


class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)


        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.handler = this
        binding.visible = View.INVISIBLE
        binding.homeId = R.id.action_home

        findNavController(R.id.nav_host).navigate(R.id.loading_fragment_dest)

        if(checkInternet(this) != null && checkInternet(this)!!){
            GlobalScope.doAsync {
                var nodeViewModel = ViewModelProviders.of(this@MainActivity).get(LocationViewModel::class.java)
                nodeViewModel.getLocationsRemote()

            }
        }
        else{
            Toast.makeText(this, "Connect to the internet for a better experience",Toast.LENGTH_SHORT).show()
        }
        if (userViewModel.user == null) {
            findNavController(R.id.nav_host).navigate(R.id.login_fragment_dest)
        } else {
            findNavController(R.id.nav_host).navigate(R.id.route_fragment_dest)
            binding.visible = View.VISIBLE
            findNavController(R.id.nav_host).navigate(R.id.route_fragment_dest, null, options)


        }
    }

    private val options = navOptions {
        anim {
            enter = R.anim.abc_popup_enter
            exit = R.anim.abc_fade_out
            popEnter = R.anim.abc_popup_enter
            popExit = R.anim.abc_popup_exit
        }
    }

    fun onNavigationClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_recents -> {
                var args = Bundle()
                args.putInt("count",1)
                findNavController(R.id.nav_host).navigate(R.id.alternativeRouting_fragment_destination, args, options)
            }
            R.id.action_settings -> {
                findNavController(R.id.nav_host).navigate(R.id.settings_fragment_dest, null, options)
            }

            R.id.action_home -> {
                findNavController(R.id.nav_host).navigate(R.id.route_fragment_dest, null, options)
            }

            R.id.action_add -> {
                findNavController(R.id.nav_host).navigate(R.id.add_route_dest, null, options)
            }
        }
        return true
    }
    fun checkInternet(context: Context): Boolean? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? =connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected
    }

}
