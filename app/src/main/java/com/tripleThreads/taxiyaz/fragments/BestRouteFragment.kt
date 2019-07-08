package com.tripleThreads.taxiyaz.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.maps.android.PolyUtil
import com.google.maps.android.SphericalUtil
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.newRoute.Route
import kotlinx.android.synthetic.main.fragment_best_route.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.StringReader
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


const val INTERVAL: Long = 1000 * 60 * 1
const val FASTEST_INTERVAL: Long = 1000 * 60 * 1
const val ROUTE_KEY_COMMENT = "ROUTE_KEY_FOR_COMMENT"

class BestRouteFragment : Fragment(), OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var startRoute: FloatingActionButton
    private lateinit var mCurrentLocation: Location
    private lateinit var destination: LatLng
    private lateinit var origin: LatLng
    private var mDistance: Double = 0.0
    private var mDistanceLocationChanged: Double = 0.0
    private lateinit var mLastUpdateTime: String
    private lateinit var googleMap: GoogleMap
    lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    var bestRoute: MutableLiveData<Route> = MutableLiveData<Route>().apply { Route(0,"",0,0.0,0.0f, ArrayList(),ArrayList()) }




    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_best_route, container, false)
        startRoute = view.start_route
        startRoute.setOnClickListener {
            calculatePathLeft()
            startLocationUpdates()
            drawPath()

        }

        // cant bind to google maps so directly accessed
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync { mMap ->
            googleMap = mMap

            googleMap.isMyLocationEnabled = true
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())

            mFusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    mCurrentLocation = location

                }

                // For dropping a marker at a point on the Map
                val latLng = LatLng(mCurrentLocation.latitude, mCurrentLocation.longitude)
                googleMap.addMarker(MarkerOptions().position(latLng).title("Marker Title").snippet("Marker Description"))


                // For zooming automatically to the location of the marker
                val cameraPosition = CameraPosition.Builder().target(latLng).zoom(12f).build()
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

            }
        }

        return view
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationResult!!.lastLocation
            onLocationChanged(locationResult.lastLocation)

        }
    }

    override fun onMapReady(mMap: GoogleMap?) {
        googleMap = mMap!!
        googleMap.uiSettings.isZoomControlsEnabled = true
    }


    private fun startLocationUpdates() {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = INTERVAL
        mLocationRequest.fastestInterval = FASTEST_INTERVAL
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(this.requireContext())
        settingsClient.checkLocationSettings(locationSettingsRequest)

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireContext())
        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())

    }

    private fun stopLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback)

    }

    override fun onLocationChanged(location: Location?) {
        mCurrentLocation = location!!
        origin = LatLng(mCurrentLocation.latitude, mCurrentLocation.longitude)
        //update the ui from here
        mDistanceLocationChanged = SphericalUtil.computeDistanceBetween(origin, destination)
        val date: Date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("hh:mm:ss a")
        mLastUpdateTime = sdf.format(date)
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync { mMap ->
            googleMap = mMap
            val latLang = LatLng(mCurrentLocation.latitude, mCurrentLocation.longitude)
            googleMap.addMarker(MarkerOptions().position(latLang).title("Updated at").snippet(mLastUpdateTime))
        }

    }

    override fun onConnected(p0: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use Filed | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this.requireContext(), "connection failed", Toast.LENGTH_SHORT).show()
    }


    private fun getURL(from: LatLng, to: LatLng): String {
        val origin = "origin=" + from.latitude + "," + from.longitude
        val dest = "destination=" + to.latitude + "," + to.longitude
        val sensor = "sensor=false"
        val params = "$origin&$dest&$sensor"
        return "https://maps.googleapis.com/maps/api/directions/json?$params&key=AIzaSyCdEVa9S65-lcJHoYWUq1MHOrrbjjohd1k"
    }

    @SuppressLint("MissingPermission")
    private fun drawPath() {
        val latLngBound = LatLngBounds.Builder()
        val options = PolylineOptions()
        options.color(Color.RED)
        options.width(5f)


        destination = LatLng(9.59306, 41.86611)
        origin = LatLng(mCurrentLocation.latitude, mCurrentLocation.longitude)
        val url = getURL(origin, destination)
        mDistance = SphericalUtil.computeDistanceBetween(origin, destination)

        doAsync {
            val result = URL(url).readText()
            uiThread {

                val klaxon = Klaxon()
                val json = klaxon.parseJsonObject(StringReader(result))
                val routes = json.array<JsonObject>("routes")
                val points = routes!!["legs"]["steps"][0] as JsonArray<JsonObject>
                val polyPts = points.flatMap {
                    PolyUtil.decode(it.obj("polyline")?.string("points"))!!
                }

                options.add(destination)
                latLngBound.include(destination)
                for (point in polyPts) {
                    options.add(point)
                    latLngBound.include(point)
                }
                options.add(origin)
                latLngBound.include(origin)
                googleMap.addPolyline(options)
                val bounds = latLngBound.build()
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))


            }
        }

    }

    private fun calculatePathLeft() {
        //display the comment and rate fragment every 25% of the path traveled
        //if the distance is equal to 25% of the distancelocationchanged

        val dialog = CommentAndRatingFragment()
        val bundle = Bundle()
        bundle.putString(ROUTE_KEY_COMMENT, "")
        dialog.arguments = bundle
        dialog.show(
            fragmentManager!!,
            "add_comment_dialog"
        )

    }
}
