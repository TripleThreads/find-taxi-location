package com.tripleThreads.taxiyaz.fragments.add_route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.databinding.FragmentBottomDialogBinding


class BottomDialogFragment : BottomSheetDialogFragment() {
    lateinit var locationName: TextInputEditText
    lateinit var gpsLocation: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentBottomDialogBinding>(
            inflater,
            R.layout.fragment_bottom_dialog,
            container,
            false
        )

        val latitude = arguments?.getDouble(LATITUDE)
        val longitude = arguments?.getDouble(LONGITUDE)

        val location = Location(1, "", latitude!!, longitude!!)

        binding.location = location

        val view = binding.root

        return view
    }
}