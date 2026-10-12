package com.example.jsonplaceholder.domain.usecase

import com.example.jsonplaceholder.domain.model.Comment
import com.example.jsonplaceholder.domain.repository.PostRepository

import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: Int): List<Comment> {
        return repository.getComments(postId)
    }
}
