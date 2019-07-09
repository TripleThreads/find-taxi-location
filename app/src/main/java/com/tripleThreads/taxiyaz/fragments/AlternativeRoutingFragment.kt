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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.RouteListAdapter
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.data.newRoute.Route
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import kotlinx.android.synthetic.main.fragment_alternative_routing.view.*
import kotlinx.android.synthetic.main.fragment_route.*
import java.util.ArrayList


class AlternativeRoutingFragment : Fragment() {
     var viewModel: RouteViewModel? = null

    var routes: MutableLiveData<List<Route>> = MutableLiveData<List<Route>>().apply { value = emptyList() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_alternative_routing, container, false)



        val activity1 = activity as Context
        var recyclerView = view.alternate_route_recycler_view
        var adapter = RouteListAdapter(activity1, object : OnItemClickListener {
            override fun onItemClick(route: Route) {
                val args = Bundle()
                args.putSerializable("route",route)
                findNavController().navigate(R.id.comments_fragment_dest,args)

            }
        })




        recyclerView.layoutManager = LinearLayoutManager(activity1)
        recyclerView.adapter =  adapter
        recyclerView.setHasFixedSize(true)





        routes.observe(this, Observer {
            adapter.setRoutes(it!!)
        })

        if( arguments?.getInt("count") == 1){
            //Toast.makeText(context,"all here",Toast.LENGTH_SHORT).show()
            viewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)
            viewModel?.getAll()
            viewModel?.allRoutes?.observe(this, Observer {
                adapter.setRoutes(it!!)
            })

        }
        else if( arguments?.getInt("count") == 2){
            //Toast.makeText(context,"all here",Toast.LENGTH_SHORT).show()
            viewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)
            viewModel?.getBookMarked()
            viewModel?.allRoutes?.observe(this, Observer {
                adapter.setRoutes(it!!)
            })

        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.routes.apply { emptyList<Route>() }
    }









}
interface OnItemClickListener {
    fun onItemClick(route: Route)
}
