package com.tripleThreads.taxiyaz.fragments.add_route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.databinding.FragmentTaxiToNodeBinding
import kotlinx.android.synthetic.main.available_taxi_layout.view.*
import kotlinx.android.synthetic.main.fragment_taxi_to_node.view.*
import kotlinx.android.synthetic.main.fragment_taxi_to_node.view.available_taxi_ll


class AddTaxiToNodeFragment : Fragment(), RegisterTaxiEventListeners {
    private lateinit var availableTaxiLL: LinearLayout // list of available taxis holder
    private var dynamicAvailableTaxi = arrayListOf<View>()
    private lateinit var availableTx: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTaxiToNodeBinding>(
            inflater,
            R.layout.fragment_taxi_to_node,
            container,
            false
        )
        binding.nodeListViews = ArrayList<View>()
        binding.handler = this
        val view = binding.root

        // this part has dynamically generated view part on runtime so we couldn't bind with data binder

        availableTaxiLL = view.available_taxi_ll
        availableTx = LayoutInflater.from(context).inflate(R.layout.available_taxi_layout, null)
        availableTaxiLL.addView(availableTx)
        dynamicAvailableTaxi.add(availableTx)

        listenerBinder(availableTx)

        return view
    }

    // dynamically generated views couldn't bind with view model
    private fun listenerBinder(availableTaxi: View) {

        availableTaxi.location_price.setOnFocusChangeListener { _, b ->
            if (b) {
                for (_view in dynamicAvailableTaxi) {
                    Toast.makeText(context, _view.location_name.text.toString(), Toast.LENGTH_LONG).show()
                    if (_view.location_name.text.toString().isEmpty() || dynamicAvailableTaxi.size > 1
                        && _view.location_price.text.toString().isEmpty() && availableTaxi != _view) {
                        return@setOnFocusChangeListener
                    }
                }
                val availableTx = LayoutInflater.from(context).inflate(R.layout.available_taxi_layout, null)
                availableTaxiLL.addView(availableTx)
                dynamicAvailableTaxi.add(availableTx)
                listenerBinder(availableTx)
            }
        }

    }

    override fun onSubmit() {
        for (_view in dynamicAvailableTaxi) {
            Toast.makeText(context, _view.location_name.text.toString(), Toast.LENGTH_LONG).show()

            // this is where you can access those data
        }
    }
}


interface RegisterTaxiEventListeners {
    fun onSubmit()
}
