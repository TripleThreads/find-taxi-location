package com.tripleThreads.taxiyaz


import androidx.test.core.app.ApplicationProvider
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(
    JUnit4::class)


class AdminDaoTest {
    private var database = TxYzDatabase.getDatabase(ApplicationProvider.getApplicationContext())

    @Before
    fun initDb() {

        database = TxYzDatabase.getDatabase(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun some(){
        assert(true)
    }

    @After
    fun closeDb() = database.close()


}

