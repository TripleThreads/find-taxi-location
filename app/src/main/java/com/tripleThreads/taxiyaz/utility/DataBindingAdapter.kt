package com.tripleThreads.taxiyaz.utility

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods


@BindingMethods(
    BindingMethod(
        type = BottomNavigationView::class,
        attribute = "app:onNavigationItemSelected",
        method = "setOnNavigationItemSelectedListener"
    ),
    BindingMethod(
        type = BottomNavigationView::class,
        attribute = "app:selectedItemPosition",
        method = "setSelectedItemId"
    )
)
class DataBindingAdapter