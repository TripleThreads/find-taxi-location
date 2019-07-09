package com.tripleThreads.taxiyaz.data.node



class APINode(
    val id: Long,
    var name: String,
    var latitude: Double,
    var longitude: Double,
    val availableNode: AvailableNode?

){
    fun convertToNode(): Node{
        return Node(this.id, this.name, this.latitude, this.longitude)
    }
}
