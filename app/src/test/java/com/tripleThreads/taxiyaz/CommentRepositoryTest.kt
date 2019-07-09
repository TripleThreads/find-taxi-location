package com.tripleThreads.taxiyaz


import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.comment.Comment
import com.tripleThreads.taxiyaz.data.comment.CommentDao
import com.tripleThreads.taxiyaz.data.newRoute.Route
import com.tripleThreads.taxiyaz.data.newRoute.RouteDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*
import kotlin.collections.ArrayList

@ExperimentalCoroutinesApi
@RunWith(
    JUnit4::class)
class CommentRepositoryTest {
    lateinit var routeDao:RouteDao
    lateinit var commentDao: CommentDao

    lateinit var route: Route
    lateinit var comment: Comment

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        commentDao = TxYzDatabase.getDatabase(context).commentDao()
        routeDao = TxYzDatabase.getDatabase(context).routeDao()
        route = Route(1,"Testing Title",12,2.50,2.5f, ArrayList(),ArrayList())
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