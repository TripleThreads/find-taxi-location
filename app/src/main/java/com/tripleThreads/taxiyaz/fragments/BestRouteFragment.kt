package com.tripleThreads.taxiyaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.RouteListAdapter
import kotlinx.android.synthetic.main.fragment_best_route.view.*

class BestRouteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_best_route, container, false)

        val recyclerView = view.best_route_recycler_view
        var routeAdapter = RouteListAdapter(this!!.activity!!)
        recyclerView.adapter = routeAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.activity)


        return view
    }


}
