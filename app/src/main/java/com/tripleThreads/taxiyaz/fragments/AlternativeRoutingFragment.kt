package com.tripleThreads.taxiyaz.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.RouteListAdapter
import kotlinx.android.synthetic.main.fragment_alternative_routing.view.*
import com.tripleThreads.taxiyaz.Network.DataServiceGenerator
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.viewModel.routeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException


class AlternativeRoutingFragment : Fragment() {
    lateinit var viewModel: routeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_alternative_routing, container, false)
        val activity1 = activity as Context
        var recyclerView = view.alternate_route_recycler_view
        var adapter = RouteListAdapter(activity1)
        fetchRoute()
        adapter.getRoutes()
        recyclerView.adapter =  adapter
        recyclerView.layoutManager = LinearLayoutManager(activity1)
        recyclerView.setHasFixedSize(true)
        //Log.e("ATTACHMENT","attached")


        viewModel = ViewModelProviders.of(this).get(routeViewModel::class.java)


        viewModel.allRoutes.observe(this, Observer {
            routes -> routes.let { adapter.setRoutes(routes) }

        })


        return view
    }

    fun fetchRoute(){
        GlobalScope.launch(Dispatchers.IO) {

            try {


                val service = DataServiceGenerator().createRouteService()
                val response: Response<List<Route>> = service?.getAllRoutes()!!.await()
                val routes = response.body()

                if (routes != null) {
                    withContext(Dispatchers.Main) {
                        updateList(routes)
                    }
                }
            }
            //will fix after my nap
            catch (e: SocketTimeoutException){
                var array = ArrayList<Location>()
                array.add(Location(1,12.1,12.4))
                val routes =listOf(
                    Route(1,"First one",3,33.2, array),
                    Route(1,"First one",3,33.2, array),
                    Route(1,"First one",3,33.2, locations = array),
                    Route(1,"First one",3,33.2, locations = array),
                    Route(1,"First one",3,33.2, locations = array)
                )
                updateList(routes)

            }
        }


    }



    private fun updateList(routes: List<Route>) {
        var array = ArrayList<Location>()
        array.add(Location(1,12.1,12.4))
        viewModel.deleteAll()
        routes.forEach { route -> route.locations =  array}
        routes.forEach { route -> viewModel.insert(route) }



    }


}
