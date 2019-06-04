package com.tripleThreads.taxiyaz.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {
    lateinit var userViewModel: UserViewModel

    lateinit var phoneNumber: EditText
    lateinit var userName: EditText
    lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login,container,false)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        phoneNumber = view.phone_number
        userName = view.user_name
        registerButton = view.ContinueBtn


        registerButton.setOnClickListener{
            showDialog(getFields())
        }

        return view
    }

    private fun getFields(): User {
        return User(
            name = userName.text.toString(),
            phoneNumber = phoneNumber.text.toString()
        )
    }

    private fun showDialog(user: User){
        var dialog: AlertDialog.Builder?=null
        dialog = AlertDialog.Builder(context!!)
            .setMessage("Are you sure (+251) ${user.phoneNumber} is your number?   ")

        if((user.phoneNumber.length == 9 && user.phoneNumber.startsWith('9')) || (user.phoneNumber.length == 10 &&
                    user.phoneNumber.startsWith('0'))){
            dialog.setMessage("Are you sure (+251) ${user.phoneNumber} is your number?   ")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which ->
                // replace fragment

                userViewModel.insert(user)

                val routeFragment = RouteFragment()
                fragmentManager!!.beginTransaction()
                    .replace(R.id.main_frame, routeFragment)
                    .commit()
                activity!!.bottom_navigation.visibility = View.VISIBLE

            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener{ dialog, which ->
                Toast.makeText(context,"No", Toast.LENGTH_SHORT).show()
            })
            val alert = dialog.create()
            alert.setTitle("Confirm your phone number")
            alert.show()
        }
        else if(user.phoneNumber.isEmpty()){
            phone_number.error = "Phone Number can't be empty"

        }
        else{
            Toast.makeText(context,"Invalid Phone Number", Toast.LENGTH_SHORT).show()
        }
    }

}
