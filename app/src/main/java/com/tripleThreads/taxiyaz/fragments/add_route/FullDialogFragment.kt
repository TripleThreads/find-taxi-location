package com.tripleThreads.taxiyaz.fragments.add_route

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.gms.maps.GoogleMap
import com.tripleThreads.taxiyaz.R


val TAG = "FULL SCREEN DIALOG"
class FullDialogFragment : DialogFragment() {

    var mMap: GoogleMap? = null

    init {

    }


    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_full_dialog, container, false)
        return rootView
    }




}