package com.example.jsonplaceholder.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jsonplaceholder.domain.usecase.GetPostUseCase

class PostDetailsViewModelFactory(
    private val getPostUseCase: GetPostUseCase,
    private val postId: Int
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(PostDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostDetailsViewModel(
                getPostUseCase = getPostUseCase,
                postId = postId
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
