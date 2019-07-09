package com.tripleThreads.taxiyaz.fragments.add_route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.node.NodeEdge
import com.tripleThreads.taxiyaz.databinding.FragmentTaxiToNodeBinding
import com.tripleThreads.taxiyaz.viewModel.LocationViewModel
import kotlinx.android.synthetic.main.available_taxi_layout.view.*
import kotlinx.android.synthetic.main.fragment_taxi_to_node.view.*
import kotlinx.android.synthetic.main.fragment_taxi_to_node.view.available_taxi_ll

/**
 * this class stores dynamically generated views at run time.. so it is not ideal to use databinding
 * */
class AddTaxiToNodeFragment : Fragment(), RegisterTaxiEventListeners {
    private var dynamicAvailableTaxi = arrayListOf<View>()
    private lateinit var taxiBegin: AutoCompleteTextView
    private lateinit var availableTx: View
    private lateinit var binding: FragmentTaxiToNodeBinding
    private lateinit var locationViewModel: LocationViewModel
    private var titles: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_taxi_to_node,
            container,
            false
        )

        binding.nodeListViews = ArrayList<View>()
        binding.handler = this
        val view = binding.root

        taxiBegin = view.taxi_location
        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        locationViewModel.getLocations()
        locationViewModel.allLocations.observe(this, Observer {
            it!!.forEach { node -> titles.add(node.name) }
        })

        ArrayAdapter(context,android.R.layout.simple_list_item_1,titles).also {
                arrayAdapter -> taxiBegin.setAdapter(arrayAdapter)
        }

        // this part has dynamically generated view part on runtime so we couldn't bind with data binder

        availableTx = LayoutInflater.from(context).inflate(R.layout.available_taxi_layout, null)
        ArrayAdapter(context,android.R.layout.simple_list_item_1,titles).also {
                arrayAdapter -> availableTx.location_name.setAdapter(arrayAdapter)
        }
        binding.root.available_taxi_ll.addView(availableTx)
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
                ArrayAdapter(context,android.R.layout.simple_list_item_1,titles).also {
                        arrayAdapter -> availableTx.location_name.setAdapter(arrayAdapter)
                }
                binding.root.available_taxi_ll.addView(availableTx)
                dynamicAvailableTaxi.add(availableTx)
                listenerBinder(availableTx)
            }
        }

    }

    override fun onSubmit() {
        if(taxiBegin.text.toString().trim() != "") {
            var nodeEdges= ArrayList<NodeEdge>()

            for (_view in dynamicAvailableTaxi) {
//                Toast.makeText(context, _view.location_name.text.toString(), Toast.LENGTH_LONG).show()
                if(_view.location_name.text.toString().trim() != "" && _view.location_price.text.toString().trim() != ""){
                    var temp = NodeEdge(_view.location_name.text.toString(), _view.location_price.text.toString().toDouble())
                    nodeEdges.add(temp)
                }
                else
                    break
            }
            if(nodeEdges.size > 0){
                locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
                locationViewModel.addAvailableNode(taxiBegin.text.toString(),nodeEdges)
                Toast.makeText(context, "Thank you for your contribution",Toast.LENGTH_SHORT).show()

            }


        }
        else{
            Toast.makeText(context, "Please fill out all the values", Toast.LENGTH_LONG).show()

        }
    }
}


interface RegisterTaxiEventListeners {
    fun onSubmit()
}
