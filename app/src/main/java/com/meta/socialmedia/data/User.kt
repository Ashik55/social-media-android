package com.meta.socialmedia.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String?,
    val email: String?,
    val image: String?,
    val bio: String?,
):Parcelable