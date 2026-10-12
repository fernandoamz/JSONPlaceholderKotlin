package com.example.jsonplaceholder.presentation.comments

data class Comment (
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)
