package com.tripleThreads.taxiyaz.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
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


class AlternativeRoutingFragment : Fragment() {
    lateinit var viewModel: RouteViewModel

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
        recyclerView.adapter =  adapter
        recyclerView.layoutManager = LinearLayoutManager(activity1)
        recyclerView.setHasFixedSize(true)


        viewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)

        Log.d("check", "In fragment")
        viewModel.getRoutes("MexicoTo6kilo")

        viewModel.allRoutes.observe(this, Observer {
            routes -> routes.let { adapter.setRoutes(routes) }

        })


        return view
    }





    private fun updateList(routes: List<Route>) {
        var array = ArrayList<Location>()
        array.add(Location(1,"Test",12.1,12.4))
        viewModel.deleteAll()
        routes.forEach { route -> route.locations =  array}
        routes.forEach { route -> viewModel.insert(route) }
    }




}
interface OnItemClickListener {
    fun onItemClick(route: Route)
}
