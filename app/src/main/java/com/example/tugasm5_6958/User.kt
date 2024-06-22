package com.example.tugasm5_6958

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name:String,
    val username:String,
    val password:String,
    val role:String,
    val description:String?,
    val lengthOfEmployment:String?,
    var price:Int?,
    var pendapatan:Int?
): Parcelable