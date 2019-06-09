package com.tripleThreads.taxiyaz


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.databinding.RouteCardViewItemBinding
import com.tripleThreads.taxiyaz.fragments.OnItemClickListener
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import kotlinx.android.synthetic.main.route_card_view_item.view.*

class RouteListAdapter(context: Context, onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<RouteViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var routes = emptyList<Route>()
    private val onClick = onItemClickListener

    lateinit var viewModel:RouteViewModel
    fun  getRoutes(): List<Route>{
        routes = emptyList()
        return routes;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val recyclerViewItem = inflater.inflate(R.layout.route_card_view_item, parent, false)


        return RouteViewHolder(recyclerViewItem)
    }

    override fun getItemCount() = routes.size

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val route = routes[position]
        holder.routeTitle.text = route.title
       // holder.routeGeo.text = route.locations[0].latitude.toString() +" "+route.locations[0].longitude.toString()
        holder.routeHops.text = route.hops.toString()
        holder.routePrice.text = route.price.toString()

        holder.bind(routes[position],onClick)
        //viewModel = ViewModelProviders.of(AlternativeRoutingFragment()).get(RouteViewModel::class.java)
    }

    internal fun setRoutes(routes: List<Route>){
      this.routes = routes
        notifyDataSetChanged()

    }

}

class RouteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){


    fun bind(route: Route, onClick: OnItemClickListener) {
            itemView.setOnClickListener{
                onClick.onItemClick(route)
            }
    }

    val routeTitle = itemView.location_title
    val routeGeo = itemView.rating
    val routeHops = itemView.location_hops
    val routePrice = itemView.location_price

}
