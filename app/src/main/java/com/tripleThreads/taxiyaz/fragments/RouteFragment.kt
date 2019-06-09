package com.tripleThreads.taxiyaz.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_route.*
import kotlinx.android.synthetic.main.fragment_route.view.*


class RouteFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_route, container, false)






        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(BestRouteFragment(), "Best Route")
        adapter.addFragment(AlternativeRoutingFragment(), "Alternative Routes")
        view.view_pager_route.adapter = adapter
        view.tabs.setupWithViewPager(view_pager_route)
        view.tabs.getTabAt(0)?.setIcon(R.drawable.ic_best_user_black_24dp)
        view.tabs.getTabAt(1)?.setIcon(R.drawable.ic_optional_black_24dp)
    }


}
