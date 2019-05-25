package com.tripleThreads.taxiyaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import com.tripleThreads.taxiyaz.R
import kotlinx.android.synthetic.main.fragment_best_route.view.*


class BestRouteFragment : Fragment() {
    private lateinit var mapboxMapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_best_route, container, false)

        mapboxMapView = view.best_route_map_view
        mapboxMapView.getMapAsync {
            mapboxMap -> mapboxMap.setStyle(Style.MAPBOX_STREETS)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        mapboxMapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapboxMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapboxMapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapboxMapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapboxMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapboxMapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapboxMapView.onSaveInstanceState(outState)
    }

}
