package com.example.jsonplaceholder.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jsonplaceholder.domain.usecase.GetPostsUseCase

class PostsViewModelFactory(
    private val getPostsUseCase: GetPostsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PostsViewModel(getPostsUseCase) as T
        }


        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }
}
