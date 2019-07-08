package com.tripleThreads.taxiyaz


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripleThreads.taxiyaz.data.newRoute.Route
import com.tripleThreads.taxiyaz.databinding.RouteCardViewItemBinding
import com.tripleThreads.taxiyaz.fragments.OnItemClickListener
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel

class RouteListAdapter(context: Context, onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RouteViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var routes = emptyList<Route>()
    private val onClick = onItemClickListener

    lateinit var viewModel: RouteViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val recyclerViewItem = DataBindingUtil.inflate<RouteCardViewItemBinding>(
            inflater,
            R.layout.route_card_view_item,
            parent, false
        )

        return RouteViewHolder(recyclerViewItem)
    }

    override fun getItemCount() = routes.size

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        //viewModel = ViewModelProviders.of(AlternativeRoutingFragment()).get(RouteViewModel::class.java)
        holder.routeCardViewBinding.route = routes[position]
        holder.bind(routes[position], onClick)
        Log.e("bind", routes[position].title)
    }

    internal fun setRoutes(routes: List<Route>) {
        this.routes = routes
        Log.e("setRoutes", routes.toString())
        notifyDataSetChanged()
        Log.e("getItemCount", itemCount.toString())
    }

}

class RouteViewHolder(val routeCardViewBinding: RouteCardViewItemBinding) :

    RecyclerView.ViewHolder(routeCardViewBinding.root) {


    fun bind(route: Route, onClick: OnItemClickListener) {
        routeCardViewBinding.root.setOnClickListener {
            onClick.onItemClick(route)
        }
    }

}
