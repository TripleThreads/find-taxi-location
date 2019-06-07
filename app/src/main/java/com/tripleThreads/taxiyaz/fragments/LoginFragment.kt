package com.tripleThreads.taxiyaz.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
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
    private val locationAccessKey = 1

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationAccessKey) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                findNavController().navigate(R.id.route_fragment_dest)
                activity!!.bottom_navigation.visibility = View.VISIBLE

            } else {

            }
        }
    }

    private fun checkPermission(){


        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this.requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {

            val builder = AlertDialog.Builder(this.requireContext())
            builder.setTitle("Required Permissions")
            builder.setMessage("This app requires location permission to  guide you through different routes. " +
                    "Please grant the permission.")
            builder.setPositiveButton("Ok")
            { dialog, which ->
               requestPermissions(
                   arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    locationAccessKey
                )
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, which -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        } else {

            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationAccessKey
            )


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login,container,false)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        phoneNumber = view.phone_number
        userName = view.user_name
        registerButton = view.ContinueBtn


        if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            checkPermission()

        }


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
                if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    userViewModel.insert(user)
                    findNavController().navigate(R.id.route_fragment_dest)
                    activity!!.bottom_navigation.visibility = View.VISIBLE
                }
                else{
                    val builder = AlertDialog.Builder(this.requireContext())
                    builder.setTitle("Required Permissions")
                    builder.setMessage("In order to go further you need to allow the location. PLease do as you are asked")
                    builder.setPositiveButton("Ok")
                    { _, _ ->
                        requestPermissions(
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            locationAccessKey
                        )
                        userViewModel.insert(user)
                    }
                    builder.setNegativeButton(
                        "Cancel"
                    ) { _, _ -> dialog.cancel() }
                    val alert = builder.create()
                    alert.show()

                }

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
