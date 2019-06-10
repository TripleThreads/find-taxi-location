package com.tripleThreads.taxiyaz.data.APIHelpers

import com.tripleThreads.taxiyaz.data.location.Location

data class NodeConnection (val start: Location, val dest:Location, val price: Double)