package com.tripleThreads.taxiyaz.data.APIHelpers


import com.google.gson.annotations.SerializedName
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.data.route.Route
import java.io.Serializable

class RouteAPI (
   val routeId:Long,
   val title: String,
    val hops: Int,
    val price: Double,
    val start: Location,
    val destination: Location,
    @SerializedName("stops") var locations: ArrayList<Location>
): Serializable{
    fun convertToRoute(): Route {
        val route =
            Route(this.routeId, this.title, this.start.id,this.destination.id,this.hops, this.price, this.locations)
        return route
    }

    fun getStartLocation():Location{
        return this.start
    }
    fun getDestinationLocation(): Location{
        return this.destination
    }

}
