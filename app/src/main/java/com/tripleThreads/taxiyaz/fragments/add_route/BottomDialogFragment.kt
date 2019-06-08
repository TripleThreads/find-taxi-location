package com.tripleThreads.taxiyaz.fragments.add_route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.location.Location
import kotlinx.android.synthetic.main.fragment_full_dialog.*
import kotlinx.android.synthetic.main.fragment_full_dialog.view.*


class BottomDialogFragment : BottomSheetDialogFragment() {
    lateinit var locationName: TextInputEditText
    lateinit var gpsLocation: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_full_dialog, container, false)
        val latitude = arguments?.getDouble(LATITUDE)
        val longitude = arguments?.getDouble(LONGITUDE)

        locationName = view.location_name
        gpsLocation = view.gps_location

        submit_node.setOnClickListener {
            if (gpsLocation.checkedRadioButtonId == R.id.radio_pinned) {
                val location = Location(1, latitude!!, longitude!!)
                TODO("call view models to send it db")
            }
        }
        return view
    }
}