package com.tripleThreads.taxiyaz

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.runner.AndroidJUnit4
import com.tripleThreads.taxiyaz.fragments.add_route.CreateNewNode
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BottomDialogUITest {
    @Test
    fun addNode() {
        launchFragmentInContainer<CreateNewNode>(Bundle(), R.style.AppTheme)
    }
}