package com.tripleThreads.taxiyaz.repository

import android.os.AsyncTask
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tripleThreads.taxiyaz.data.node.*
import com.tripleThreads.taxiyaz.network.NodeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LocationRepository(private val dao: NodeDao, private val nodeService: NodeService?) {
    var allLocations: LiveData<List<Node>> = dao.getAllLocations()

    @WorkerThread
    private fun cache(node: Node) {
        dao.insertLocation(node)
    }

    @WorkerThread
    fun getLocationFromAPI(){
        GlobalScope.launch(Dispatchers.IO){
            if (nodeService != null){
                val response: Response<List<APINode>> = nodeService.getAllLocations()!!.await()
                val nodes = response.body()
                if(nodes != null){
                    withContext(Dispatchers.IO){
                        nodes.forEach {
                                node -> cache(node.convertToNode())
                            Log.d("Adding",node.name)
                        }
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAll(): LiveData<List<Node>>{
        //getLocationFromAPI()
        allLocations = dao.getAllLocations()
        return allLocations

    }

    @WorkerThread
    fun getAllNodes(): List<Node>{
        return dao.getAllNodes()
    }


    @WorkerThread
    fun insert(node: Node):Boolean{
        return if (insertLocationToAPI(node)){
            dao.insertLocation(node)
            true
        } else{
            false
        }

    }

    @WorkerThread
    fun insertLocationToAPI(node: Node): Boolean{
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(nodeService != null){
                nodeService.addLocation(node)
                added = true
            }
        }
        return added
    }

    fun addAvailableNode(nodeTitle: String, nodeEdges: List<NodeEdge>) {

            var startNode = dao.getLocationByName(nodeTitle)
            var availableNodes =ArrayList<AvailableNode>()
            nodeEdges.forEach {
                var destination=dao.getLocationByName(it.destinationName)
                var availableNode = AvailableNode(0,destination, it.price)
                availableNodes.add(availableNode)
                Log.d("edgenode","$availableNode")
            }
            if (nodeService != null){
                nodeService.addEdge(startNode.id, availableNodes)

                Log.d("edgenode","edge added")
            }




    }


}