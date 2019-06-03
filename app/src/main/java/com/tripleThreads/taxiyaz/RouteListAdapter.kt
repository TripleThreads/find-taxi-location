package com.tripleThreads.taxiyaz


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.tripleThreads.taxiyaz.Data.Route
import com.tripleThreads.taxiyaz.fragments.AlternativeRoutingFragment
import com.tripleThreads.taxiyaz.viewModel.routeViewModel
import kotlinx.android.synthetic.main.route_card_view_item.view.*

class RouteListAdapter(context: Context): RecyclerView.Adapter<RouteViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    var routes = emptyList<Route>()

    lateinit var viewModel:routeViewModel
    fun  getRoutes(){
        //get route from database here

        routes = listOf(
            Route("First one",10.11,33.2, 3, 3.50),
            Route("Second one",1.11,33.2, 5, 3.50),
            Route("Third one",3.11,33.2, 7, 4.0),
            Route("Fourth one",10.01,33.2, 1, 3.50),
            Route("Bekaaa one",30.4,33.2, 0, 13.50),
            Route("Last one",10.11,33.2, 4, 3.50),
            Route("Repeated one",10.11,33.2, 3, 3.50),
            Route("First one",10.11,33.2, 3, 3.50)

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
        holder.routeGeo.text = route.latitude.toString() +" "+route.logitude
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
