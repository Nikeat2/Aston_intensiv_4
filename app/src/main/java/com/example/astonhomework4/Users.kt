package com.example.astonhomework4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val id: Int,
    val image: String,
    val name: String,
    val surname: String,
    val phoneNumber: String
) : Parcelable
