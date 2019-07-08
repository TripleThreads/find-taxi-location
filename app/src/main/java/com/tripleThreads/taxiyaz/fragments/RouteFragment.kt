package com.tripleThreads.taxiyaz.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.ViewPagerAdapter
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.databinding.FragmentRouteBinding
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import kotlinx.android.synthetic.main.fragment_route.*
import kotlinx.android.synthetic.main.fragment_route.view.*


class RouteFragment : Fragment(), OnKeyChangeListener {

     var publicViewModel: RouteViewModel? = null
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        publicViewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentRouteBinding>(
            inflater,
            R.layout.fragment_route,
            container,
            false
        )

        binding.handler = this

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bestRouteFragment = BestRouteFragment()
        val alternativeRoutingFragment = AlternativeRoutingFragment()
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(bestRouteFragment, "Best APIRoute")
        adapter.addFragment(alternativeRoutingFragment, "Alternative Routes")
        view.view_pager_route.adapter = adapter
        view.tabs.setupWithViewPager(view_pager_route)
        view.tabs.getTabAt(0)?.setIcon(R.drawable.ic_best_user_black_24dp)
        view.tabs.getTabAt(1)?.setIcon(R.drawable.ic_optional_black_24dp)
    }

    override fun onKeyChange(charSequence: CharSequence, start: Int, before: Int, count: Int): Boolean {
        if (count < 2)
            return true

        publicViewModel!!.allRoutes = publicViewModel!!.getRoutes(charSequence.toString())

        return true
    }

}

interface OnKeyChangeListener {
    fun onKeyChange(charSequence: CharSequence, start: Int, before: Int, count: Int): Boolean
}