package com.meta.socialmedia.data

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Post(
    val id: Int,
    val type: String?,
    val title: String?,
    val description: String?,
    val imageURL: String?,
    val videoURL: String?,
    val user: User?
): Parcelable

