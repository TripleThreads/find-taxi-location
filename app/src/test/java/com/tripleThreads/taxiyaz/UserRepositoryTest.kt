package com.tripleThreads.taxiyaz


import android.content.Context
import androidx.navigation.NavController
import androidx.room.Room
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.data.user.UserDao
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Test
import androidx.test.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

class UserRepositoryTest{
    val appContext = InstrumentationRegistry.getTargetContext()


    @Test
    fun insertUserTest() {
        val user = User(1,"Yeabsira Tesfaye","0922343241")
        val userFromDb = TxYzDatabase.getDatabase(appContext).userDao().getUser()

        TxYzDatabase.getDatabase(appContext).userDao().insert(user)

        assertEquals(userFromDb?.name, user.name)
        assertEquals(userFromDb?.phoneNumber,user.phoneNumber)
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

        assertEquals(userFromDb?.name, user.name)
        assertEquals(userFromDb?.phoneNumber,user.phoneNumber)
    }



}