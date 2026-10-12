package com.example.jsonplaceholder.di

import com.example.jsonplaceholder.data.remote.RetrofitClient
import com.example.jsonplaceholder.data.repository.PostRepositoryImpl
import com.example.jsonplaceholder.domain.repository.PostRepository
import com.example.jsonplaceholder.domain.usecase.GetCommentsUseCase
import com.example.jsonplaceholder.domain.usecase.GetPostsUseCase
import com.example.jsonplaceholder.domain.usecase.GetPostUseCase
class AppContainer {
    private val api = RetrofitClient.postsApi

    private val postRepository: PostRepository = PostRepositoryImpl(api)

    val getPostsUseCase: GetPostsUseCase = GetPostsUseCase(postRepository)
    val getCommentsUseCase: GetCommentsUseCase = GetCommentsUseCase(postRepository)
    val getPostUseCase: GetPostUseCase = GetPostUseCase(postRepository)
}
