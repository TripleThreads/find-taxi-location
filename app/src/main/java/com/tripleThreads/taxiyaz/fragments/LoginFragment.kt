package com.tripleThreads.taxiyaz.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.databinding.FragmentLoginBinding
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*


class LoginFragment : Fragment(), UserViewModelEventListeners {

    private lateinit var userViewModel: UserViewModel

    private val locationAccessKey = 1

    override fun onButtonClick(user: User) {
        Toast.makeText(context, user.name, Toast.LENGTH_LONG).show()
        userViewModel.insert(user)
        findNavController().navigate(R.id.route_fragment_dest)
        activity!!.bottom_navigation.visibility = View.VISIBLE
        showDialog()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationAccessKey) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            } else {
                showDialog()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            layoutInflater,
            R.layout.fragment_login,
            container,
            false
        )
        val view = binding.root
        binding.user = User(0, "user", "941634533")
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.handlers = this
        binding.lifecycleOwner = this

        if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            checkPermission()
        }
        return view
    }

    private fun showDialog() {

        if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
        } else {
            val builder = AlertDialog.Builder(this.requireContext())
            builder.setTitle("Required Permissions")
            builder.setMessage("In order to go further you need to allow the location. PLease do as you are asked")
            builder.setPositiveButton("Ok")
            { _, _ ->
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    locationAccessKey
                )
            }
            builder.setNegativeButton(
                "Cancel"
            ) { _, _ ->
                run {
                    val alert = builder.create()
                    alert.show()
                }
            }

        }
    }

    private fun checkPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this.requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {

            val builder = AlertDialog.Builder(this.requireContext())
            builder.setTitle("Required Permissions")
            builder.setMessage(
                "This app requires location permission to  guide you through different routes. " +
                        "Please grant the permission."
            )
            builder.setPositiveButton("Ok")
            { _, _ ->
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    locationAccessKey
                )
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        } else {

            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationAccessKey
            )
        }
    }


}


interface UserViewModelEventListeners {
    fun onButtonClick(user: User)
}