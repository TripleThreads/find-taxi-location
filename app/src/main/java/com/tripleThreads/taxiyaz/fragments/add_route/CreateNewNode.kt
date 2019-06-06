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


class CreateNewNode : Fragment() {
    private lateinit var googleMap: GoogleMap


    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_new_node, container, false)

        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync { mMap ->
            googleMap = mMap

            // For showing a move to my location button
            googleMap.isMyLocationEnabled = true

            // For dropping a marker at a point on the Map
            val addisAbaba = LatLng(8.9806, 38.7578)
            googleMap.addMarker(MarkerOptions().position(addisAbaba).title("Marker Title").snippet("Marker Description"))

            // For zooming automatically to the location of the marker
            val cameraPosition = CameraPosition.Builder().target(addisAbaba).zoom(12f).build()
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }

        return view
    }

}
