package com.tripleThreads.taxiyaz.fragments.add_route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.tripleThreads.taxiyaz.R
import com.google.android.gms.maps.GoogleMap
import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.maps.model.*


class AddTaxiToNodeFragment : Fragment() {
    private lateinit var googleMap: GoogleMap


    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_taxi_to_node, container, false)

        return view
    }

}
