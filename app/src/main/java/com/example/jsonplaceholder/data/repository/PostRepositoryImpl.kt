package com.example.jsonplaceholder.data.repository

import com.example.jsonplaceholder.data.mapper.toDomain
import com.example.jsonplaceholder.data.remote.PostsApi
import com.example.jsonplaceholder.domain.model.Comment
import com.example.jsonplaceholder.domain.model.Post
import com.example.jsonplaceholder.domain.repository.PostRepository


class PostRepositoryImpl(
    private val api: PostsApi
): PostRepository {
    override suspend fun getPosts(): List<Post> {
        return api.getPosts().map { it.toDomain() }
    }

    override suspend fun getComments(
        postId: Int
    ): List<Comment> = api.getComments(
        postId = postId
    ).map { it.toDomain() }

    override suspend fun getPost(id: Int): Post {
        return api.getPost(id).toDomain()
    }
}
