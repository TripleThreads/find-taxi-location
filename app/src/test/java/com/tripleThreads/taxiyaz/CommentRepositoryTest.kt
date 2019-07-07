package com.tripleThreads.taxiyaz


import com.tripleThreads.taxiyaz.data.TxYzDatabase
import org.junit.Test
import androidx.test.InstrumentationRegistry
import com.tripleThreads.taxiyaz.data.comment.Comment
import com.tripleThreads.taxiyaz.data.comment.CommentDao
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.data.route.RouteDao
import org.junit.Assert.*
import org.junit.Before
import java.util.*
import kotlin.collections.ArrayList

class CommentRepositoryTest {
    lateinit var routeDao:RouteDao
    lateinit var commentDao: CommentDao

    lateinit var route: Route
    lateinit var comment: Comment

    @Before
    fun setUp(){
        var context = InstrumentationRegistry.getTargetContext()
        commentDao = TxYzDatabase.getDatabase(context).commentDao()
        routeDao = TxYzDatabase.getDatabase(context).routeDao()
        route = Route(1,"Testing Title",12,12,12,12.0,12.0F, ArrayList())
        comment = Comment(1,12,"09","Test Comment", Date())

    }

    @Test
    fun getCurrentComments() {


        routeDao.insertRoute(route)
        commentDao.insertComment(comment)

        assertNotEquals(commentDao.getAllComments(route.routeId).value, null)
    }


    @Test
    fun insert() {
        routeDao.insertRoute(route)
        commentDao.insertComment(comment)

        assertNotEquals(commentDao.getAllComments(route.routeId).value, null)


    }

    @Test
    fun delete() {
        routeDao.insertRoute(route)
        commentDao.insertComment(comment)

        assertNotEquals(commentDao.getAllComments(route.routeId).value, null)

        commentDao.deleteComment(comment)

        assertEquals(commentDao.getAllComments(route.routeId).value, null)


    }

    @Test
    fun update() {
        routeDao.insertRoute(route)
        commentDao.insertComment(comment)

        assertNotEquals(commentDao.getAllComments(route.routeId).value, null)

        comment.comment = "New Comment"
        commentDao.updateComment(comment)

        val commentNew = commentDao.getAllComments(route.routeId).value



        assertEquals(commentNew!![0].comment ,"New Comment")

    }
}