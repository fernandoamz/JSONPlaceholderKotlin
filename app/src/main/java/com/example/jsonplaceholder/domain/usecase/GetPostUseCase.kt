package com.example.jsonplaceholder.domain.usecase

import com.example.jsonplaceholder.domain.model.Post
import com.example.jsonplaceholder.domain.repository.PostRepository

import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(id: Int): Post {
        return repository.getPost(id)
    }
}
