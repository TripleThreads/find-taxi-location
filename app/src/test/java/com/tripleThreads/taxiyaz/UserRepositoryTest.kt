package com.tripleThreads.taxiyaz


import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.user.User
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest{
    private lateinit var appContext: Context

    @Before
    fun before() {
        appContext = Mockito.mock(Context::class.java)
    }


    @Test
    fun insertUserTest() {
        val user = User(1,"Yeabsira Tesfaye","0922343241")
        val userFromDb = TxYzDatabase.getDatabase(appContext).userDao().getUser()

        TxYzDatabase.getDatabase(appContext).userDao().insert(user)

        assertEquals(userFromDb.name, user.name)
        assertEquals(userFromDb.phoneNumber,user.phoneNumber)
    }

    @Test
    fun deleteUserTest() {
        val user = User(1,"Yeabsira Tesfaye","0922343241")
        TxYzDatabase.getDatabase(appContext).userDao().insert(user)
        var userFromDb = TxYzDatabase.getDatabase(appContext).userDao().getUser()
        TxYzDatabase.getDatabase(appContext).userDao().delete(userFromDb)
        userFromDb = TxYzDatabase.getDatabase(appContext).userDao().getUser()
        assertNull(userFromDb)
    }

    @Test
    fun updateUserTest() {
        val user = User(1,"Yeabsira Tesfaye","0922343241")
        val userFromDb = TxYzDatabase.getDatabase(appContext).userDao().getUser()

        TxYzDatabase.getDatabase(appContext).userDao().update(user)

        assertEquals(userFromDb.name, user.name)
        assertEquals(userFromDb.phoneNumber,user.phoneNumber)
    }



}