package com.tripleThreads.taxiyaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BestRouteFragment(), "Best Route")
        adapter.addFragment(AlternativeRoutingFragment(), "Alternative Routes")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

}
