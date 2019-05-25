package com.tripleThreads.taxiyaz.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tripleThreads.taxiyaz.R
import kotlinx.android.synthetic.main.fragment_login.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoginFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login,container,false)

        val phoneNumber = view.phoneNo
        val cont = view.ContinueBtn

        cont.setOnClickListener{
            val pno = phoneNumber.text.toString()
            listener.onContinueButtonClicked(pno)
        }

        return view
    }

    lateinit var listener: onBtnClicked
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is onBtnClicked){
            listener = context
        }
    }
    interface onBtnClicked {
        fun onContinueButtonClicked(phone:String)
    }

    // TODO: Rename method, update argument and hook method into UI event

}
