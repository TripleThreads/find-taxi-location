package com.tripleThreads.taxiyaz.data.newRoute


import com.tripleThreads.taxiyaz.data.node.Node


data class APIRoute (
       val routeId: Long,
       val title: String,
       val hops: Int,
       val price: Double,
       val rating: Float,
       val routingNodes: APIRoutingNodes
) {
    fun convertToRoute(bookmarkerd: Boolean): Route {
        var route= Route(this.routeId, this.title, this.hops, this.price, this.rating, ArrayList(), ArrayList(), bookmarkerd)
        var routing = this.routingNodes
        while(routing.nextNode != null){
            route.latitudes.add(routing.node.latitude)
            route.longitudes.add(routing.node.longitude)
            routing = routing.nextNode

        }
        return route
    }
}

data class APIRoutingNodes (
    val id: Long,
    val node: Node,
    val price: Double,
    val nextNode: APIRoutingNodes

)
