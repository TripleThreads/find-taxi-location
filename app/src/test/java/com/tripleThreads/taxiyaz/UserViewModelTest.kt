package com.tripleThreads.taxiyaz

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(JUnit4ClassRunner::class)
class UserViewModelTest {

    @Test
    fun demo() {
        assertEquals(1, 1)
    }

    @Test
    fun insertUserTest() {
        val appContext =  InstrumentationRegistry.getInstrumentation().context
    }
}