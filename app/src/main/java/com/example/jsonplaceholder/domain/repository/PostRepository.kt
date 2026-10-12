package com.example.jsonplaceholder.domain.repository

import com.example.jsonplaceholder.domain.model.Post
import com.example.jsonplaceholder.domain.model.Comment

interface PostRepository {

    suspend fun getPosts(): List<Post>
    suspend fun getPost(id: Int): Post

    suspend fun getComments(
        postId: Int
    ): List<Comment>
}
