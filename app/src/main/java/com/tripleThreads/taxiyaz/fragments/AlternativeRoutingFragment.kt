package com.tripleThreads.taxiyaz.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.RouteListAdapter
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import kotlinx.android.synthetic.main.fragment_alternative_routing.view.*
import kotlinx.android.synthetic.main.fragment_route.*
import java.util.ArrayList


class AlternativeRoutingFragment : Fragment() {
     var viewModel: RouteViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_alternative_routing, container, false)
        val activity1 = activity as Context
        var recyclerView = view.alternate_route_recycler_view
        var adapter = RouteListAdapter(activity1, object : OnItemClickListener {
            override fun onItemClick(route: Route) {
                Toast.makeText(context, "Clicked",Toast.LENGTH_SHORT).show()
                val args = Bundle()
                args.putSerializable("route",route)
                //Navigation.createNavigateOnClickListener(R.id.action_route_fragment_dest_to_commentsFragment,args)
                findNavController().navigate(R.id.comments_fragment_dest,args)

            }
        })

        adapter.getRoutes()
      //  adapter.setRoutes(listOf(Route(1,"Test",2,3,2,1.1, 2.0F, ArrayList())))
        recyclerView.adapter =  adapter
        recyclerView.layoutManager = LinearLayoutManager(activity1)
        recyclerView.setHasFixedSize(true)

        val parent = parentFragment as RouteFragment

        parent.publicViewModel?.allRoutes?.observe(this, Observer {
                routes -> routes.let { adapter.setRoutes(routes) }})


//        parent.searchEdit.setOnKeyListener { v, keyCode, event ->
//            if(event?.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER){
//                val changed = parentFragment as RouteFragment
//                viewModel = changed.publicViewModel
//
//                if(viewModel == null){
//                    Log.d("parent", "is null")
//                }
//
//                Log.d("check", "In fragment")
//
//                viewModel?.allRoutes?.observe(this, Observer {
//
//                        routes -> routes.let {
//                    adapter.setRoutes(routes)
//                    Log.d("check", "observed")
//                }
//
//                })
//
//
//            }
//            true
//        }



        return view
    }









}
interface OnItemClickListener {
    fun onItemClick(route: Route)
}
