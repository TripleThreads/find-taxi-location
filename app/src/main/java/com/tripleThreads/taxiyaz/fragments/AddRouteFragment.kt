package com.tripleThreads.taxiyaz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.ViewPagerAdapter
import com.tripleThreads.taxiyaz.fragments.add_route.AddTaxiToNodeFragment
import com.tripleThreads.taxiyaz.fragments.add_route.CreateNewNode
import kotlinx.android.synthetic.main.fragment_route.*
import kotlinx.android.synthetic.main.fragment_route.view.*

class AddRouteFragment : Fragment() {

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_route, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(AddTaxiToNodeFragment(), "Add taxi to Node")
        adapter.addFragment(CreateNewNode(), "Create New Node")
        view.view_pager_route.adapter = adapter
        view.tabs.setupWithViewPager(view_pager_route)
        view.tabs.getTabAt(0)?.setIcon(R.drawable.ic_local_taxi_black_24dp)
        view.tabs.getTabAt(1)?.setIcon(R.drawable.ic_add_location_black_24dp)
    }
}
