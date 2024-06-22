package com.example.tugasm5_6958

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val id: Int,
    val customer: String,
    val montir: String,
    var status: String
): Parcelable
