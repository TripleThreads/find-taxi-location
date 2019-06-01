package com.tripleThreads.taxiyaz.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tripleThreads.taxiyaz.Data.Route
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login,container,false)

        val phoneNumber = view.phone_number
        val cont = view.ContinueBtn


        cont.setOnClickListener{
            val pno = phoneNumber.text.toString()
            showDialog(pno)
        }

        return view
    }

    companion object {
        fun newInstance(phone: String):LoginFragment{

            var args = Bundle()
            args.putSerializable("user",phone)
            val login=LoginFragment()
            login.arguments = args
            return login
        }
    }


    private fun showDialog(phone: String){
        var dialog: AlertDialog.Builder?=null
        dialog = AlertDialog.Builder(context!!)
            .setMessage("Are you sure (+251) ${phone} is your number?   ")

        if((phone.length == 9 && phone.startsWith('9')) || (phone.length == 10 && phone.startsWith('0'))){
            dialog.setMessage("Are you sure (+251) ${phone} is your number?   ")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which ->
                // replace fragment
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
        else if(phone.isEmpty()){
            phone_number.error = "Phone Number can't be empty"

        }
        else{
            Toast.makeText(context,"Invalid Phone Number", Toast.LENGTH_SHORT).show()
        }
    }

}
