package com.tripleThreads.taxiyaz

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mapbox.mapboxsdk.Mapbox
import com.tripleThreads.taxiyaz.fragments.LoginFragment
import com.tripleThreads.taxiyaz.fragments.NameFragment
import com.tripleThreads.taxiyaz.fragments.RouteFragment
import com.tripleThreads.taxiyaz.fragments.SettingsFragment
import com.tripleThreads.taxiyaz.viewModel.routeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity(), LoginFragment.onBtnClicked,
    NameFragment.onBtnClicked {
    override fun onContinueButtonClicked(phone: String) {
        showDialog()
    }

    override fun onDoneButtonClicked(name: String) {
        val routeFragment = RouteFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, routeFragment)
            .commit()
        bottom_navigation.visibility = View.VISIBLE
    }

    private lateinit var viewModel:routeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Mapbox.getInstance(this, resources.getString(R.string.mapbox_access_token))

        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        bottom_navigation.visibility = View.INVISIBLE

        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, loginFragment)
            .commit()

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }
    fun showDialog(){
        var dialog: AlertDialog.Builder?=null
        dialog = AlertDialog.Builder(this)
            .setMessage("Are you sure (+251) ${phoneNo.text} is your number?   ")

        if((phoneNo.text.toString().length == 9 && phoneNo.text.toString().startsWith('9')) || (phoneNo.text.toString().length == 10 && phoneNo.text.toString().startsWith('0'))){
            dialog.setMessage("Are you sure (+251) ${phoneNo.text} is your number?   ")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which ->
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
        else if(phoneNo.text.isEmpty()){
            phoneNo.error = "Phone Number can't be empty"

        }
        else{
            Toast.makeText(this,"Invalid Phone Number", Toast.LENGTH_SHORT).show()
        }
    }
}
