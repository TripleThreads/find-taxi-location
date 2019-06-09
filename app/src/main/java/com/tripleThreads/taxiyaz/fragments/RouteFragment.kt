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
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.ViewPagerAdapter
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import kotlinx.android.synthetic.main.fragment_route.*
import kotlinx.android.synthetic.main.fragment_route.view.*


class RouteFragment : Fragment() {
    lateinit var searchEdit: EditText

     var publicViewModel: RouteViewModel? = null
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_route, container, false)




        searchEdit = view.search_bar
        searchEdit.setOnKeyListener { v, keyCode, event ->
            if(event?.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER){
                var viewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)
                publicViewModel = viewModel
                viewModel.getRoutes(searchEdit.text.toString().trim())
                Log.d("check", "observed")

            }
            true
        }




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
