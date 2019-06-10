package com.tripleThreads.taxiyaz.fragments.add_route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.databinding.FragmentBottomDialogBinding
import com.tripleThreads.taxiyaz.utility.TxYzUtility
import com.tripleThreads.taxiyaz.viewModel.LocationViewModel


class BottomDialogFragment : BottomSheetDialogFragment(), BottomDialogEventListener {

    //lateinit var locationViewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)

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
        binding.handler = this
        binding.converter = TxYzUtility()

        return binding.root
    }

    override fun onButtonClick(location: Location) {
       // locationViewModel.insert(location)
        this.dismiss()
        //locationViewModel.insert(location)
        var locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        if(locationViewModel.insert(location)) {
            this.dismiss()
        } else{
            Toast.makeText(context,"Network Required", Toast.LENGTH_SHORT).show()
        }
    }
}

interface BottomDialogEventListener {
    fun onButtonClick(location: Location)
}