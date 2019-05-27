package com.tripleThreads.taxiyaz

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.mapbox.mapboxsdk.Mapbox
import com.tripleThreads.taxiyaz.data.User
import com.tripleThreads.taxiyaz.fragments.LoginFragment
import com.tripleThreads.taxiyaz.fragments.NameFragment
import com.tripleThreads.taxiyaz.fragments.RouteFragment
import com.tripleThreads.taxiyaz.fragments.SettingsFragment
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
import com.tripleThreads.taxiyaz.viewModel.routeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity(), LoginFragment.onBtnClicked,
    NameFragment.onBtnClicked {

    private lateinit var name:String
    private lateinit var phone:String
    lateinit var viewModel: UserViewModel

    override fun onContinueButtonClicked(phone: String) {
        showDialog(phone)
    }

    override fun onDoneButtonClicked(name: String) {
        val routeFragment = RouteFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, routeFragment)
            .commit()
        this.name = name
        val newUser = User(name,phone)
        //viewModel.insert(newUser)
        bottom_navigation.visibility = View.VISIBLE
        bottom_navigation.selectedItemId = R.id.action_home
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)
/*
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        if(viewModel.user != null){
        */
            bottom_navigation.visibility = View.INVISIBLE

            val loginFragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, loginFragment)
                .commit()
/*
        }


*/
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }


    private fun showDialog(phone: String){
        var dialog: AlertDialog.Builder?=null
        dialog = AlertDialog.Builder(this)
            .setMessage("Are you sure (+251) ${phone} is your number?   ")

        if((phone.length == 9 && phone.startsWith('9')) || (phone.length == 10 && phone.startsWith('0'))){
            dialog.setMessage("Are you sure (+251) ${phone} is your number?   ")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which ->
                this.phone = phone

                // replace fragment
                val nameFragment = NameFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, nameFragment)
                    .commit()

            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener{ dialog, which ->
                Toast.makeText(this,"No", Toast.LENGTH_SHORT).show()
            })
            val alert = dialog.create()
            alert.setTitle("Confirm your phone number")
            alert.show()
        }
        else if(phone.isEmpty()){
            phoneNo.error = "Phone Number can't be empty"

        }
        else{
            Toast.makeText(this,"Invalid Phone Number", Toast.LENGTH_SHORT).show()
        }
    }
}
