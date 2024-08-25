package com.meta.socialmedia.data

data class Post(
    val id: Int,
    val type: String?,
    val title: String?,
    val description: String?,
    val imageURL: String?,
    val videoURL: String?,
    val user: User?
)

