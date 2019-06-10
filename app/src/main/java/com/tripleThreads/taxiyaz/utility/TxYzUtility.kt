package com.tripleThreads.taxiyaz.utility

import androidx.databinding.InverseMethod

class TxYzUtility {
    @InverseMethod("fromDouble")
    fun toDouble(text: String): Double {
        return text.toDouble()
    }

    fun fromDouble(price: Double): String {
        return price.toString()
    }
}