package com.example.jsonplaceholder.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.domain.model.Post
import com.example.jsonplaceholder.domain.usecase.GetPostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PostDetailsUiState {
    data object Loading: PostDetailsUiState
    data class Success(val post: Post): PostDetailsUiState
    data class Error(val message: String): PostDetailsUiState
}

class PostDetailsViewModel(
    private val getPostUseCase: GetPostUseCase,
    private val postId: Int
): ViewModel() {

    private val _uiState =
        MutableStateFlow<PostDetailsUiState>(PostDetailsUiState.Loading)

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.value =
                    PostDetailsUiState.Success(getPostUseCase(postId))
            } catch (e: Exception) {
                _uiState.value =
                    PostDetailsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
