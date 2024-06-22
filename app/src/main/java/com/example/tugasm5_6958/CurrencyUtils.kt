package com.example.tugasm5_6958

import java.text.NumberFormat
import java.util.Locale

object CurrencyUtils {
    fun Int.toRupiah(): String {
        return NumberFormat.getCurrencyInstance(Locale("in","ID")).format(this)
    }
}