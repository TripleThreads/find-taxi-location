package com.tripleThreads.taxiyaz


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.fragments.AlternativeRoutingFragment
import com.tripleThreads.taxiyaz.viewModel.routeViewModel
import kotlinx.android.synthetic.main.route_card_view_item.view.*

class RouteListAdapter(context: Context): RecyclerView.Adapter<RouteViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    var routes = emptyList<Route>()

    lateinit var viewModel:routeViewModel
    fun  getRoutes(){
        //get route from database here
        val locations = ArrayList<Location>()
        locations.add(Location(1,12.0, 12.0))
        routes = listOf(
            Route(1,"First one",3,33.2, locations = locations),
            Route(1,"First one",3,33.2, locations = locations),
            Route(1,"First one",3,33.2, locations = locations),
            Route(1,"First one",3,33.2, locations = locations),
            Route(1,"First one",3,33.2, locations = locations)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val recyclerViewItem = inflater.inflate(R.layout.route_card_view_item, parent, false)

        recyclerViewItem.setOnClickListener {

            Toast.makeText(parent.context, routes[viewType].title,Toast.LENGTH_SHORT ).show()
        }

        return RouteViewHolder(recyclerViewItem)
    }

    override fun getItemCount() = routes.size

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val route = routes[position]
        holder.routeTitle.text = route.title
        holder.routeGeo.text = route.locations[0].latitude.toString() +" "+route.locations[0].longitude.toString()
        holder.routeHops.text = route.hops.toString()
        holder.routePrice.text = route.price.toString()


        //viewModel = ViewModelProviders.of(AlternativeRoutingFragment()).get(routeViewModel::class.java)
    }
}

class RouteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val routeTitle = itemView.location_title
    val routeGeo = itemView.location_geo
    val routeHops = itemView.location_hops
    val routePrice = itemView.location_price

}
