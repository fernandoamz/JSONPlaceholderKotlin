package com.example.jsonplaceholder.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("comments")
    suspend fun getComments(
        @Query("postId") postId: Int
    ): List<CommentDto>

    @GET("posts/{id}")
    suspend fun getPost(
        @Path("id") id: Int
    ): PostDto
}
