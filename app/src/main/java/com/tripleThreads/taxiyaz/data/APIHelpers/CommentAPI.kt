package com.tripleThreads.taxiyaz.data.APIHelpers

import com.tripleThreads.taxiyaz.data.comment.Comment
import java.util.*

class CommentAPI (
     val id:Long,
     val routeId: Long,
     val userId: Long,
     val comment: String){
    fun convertToComment(): Comment{
        return Comment(this.id.toInt(),this.routeId.toInt(),this.userId.toString(),this.comment, Date() )
    }
}