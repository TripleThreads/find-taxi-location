package com.tripleThreads.taxiyaz.fragments.add_route

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.databinding.FragmentNewNodeBinding
import kotlinx.android.synthetic.main.fragment_new_node.view.*

const val LATITUDE = "lat"
const val LONGITUDE = "long"
class CreateNewNode : Fragment(), FabEventListeners {


    private lateinit var fab: FloatingActionButton
    private lateinit var googleMap: GoogleMap
    private lateinit var marker: Marker
    private var latitude = 0.0
    private var longitude = 0.0

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNewNodeBinding>(
            inflater,
            R.layout.fragment_new_node,
            container,
            false
        )
        binding.handler = this
        val view = binding.root
        fab = view.floatingActionButton


        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync { mMap ->
            googleMap = mMap


            googleMap.setOnMapClickListener { latLng ->
                run {
                    val options = MarkerOptions()
                        .title("Test")
                        .position(
                            LatLng(
                                latLng.latitude,
                                latLng.longitude
                            )
                        )
                    latitude = latLng.latitude
                    longitude = latLng.longitude

                    googleMap.clear()

                    marker = mMap.addMarker(options)
                }
            }


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

    override fun onButtonClick() {
        val bottomDialogFragment = BottomDialogFragment()
        val bundle = Bundle()

        if (latitude != 0.0 || longitude != 0.0) {
            bundle.putDouble(LATITUDE, latitude)
            bundle.putDouble(LONGITUDE, longitude)
            bottomDialogFragment.arguments = bundle
        }

        bottomDialogFragment.show(
            fragmentManager!!,
            "add_node_dialog"
        )
    }

}

interface FabEventListeners {
    fun onButtonClick()
}
