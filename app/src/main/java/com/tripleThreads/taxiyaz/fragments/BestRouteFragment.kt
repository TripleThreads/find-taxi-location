package com.tripleThreads.taxiyaz.fragments

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
import android.app.Application
import android.location.Location
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.maps.model.*
import com.tripleThreads.taxiyaz.viewModel.LocationViewModel


class BestRouteFragment : Fragment() {
    private lateinit var googleMap: GoogleMap


    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_best_route, container, false)

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

        val bestRoute = parentFragment as RouteFragment
        bestRoute.publicViewModel?.allRoutes?.observe(this, Observer {
            if (it.isNotEmpty()) {
                val startId = it[0].startId
                val destId = it[0].destinationId
                setLocationOnMap(startId, destId)
            }
        })

        return view
    }

    private fun setLocationOnMap(startId: Long, destId: Long) {
        val locationViewModel = LocationViewModel(context as Application)
        val startLocation = locationViewModel.getLocationById(startId)
        val endLocation = locationViewModel.getLocationById(destId)
        //TODO Do your thing @segni

    }

}
