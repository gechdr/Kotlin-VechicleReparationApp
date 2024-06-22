package com.example.tugasm5_6958

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chat(
    val id: Int,
    val sender: String,
    val receiver: String,
    val message: String
): Parcelable
