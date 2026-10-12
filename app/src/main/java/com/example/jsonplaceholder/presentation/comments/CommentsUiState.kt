package com.example.jsonplaceholder.presentation.comments
import com.example.jsonplaceholder.domain.model.Comment

sealed interface CommentsUiState {
    data object Loading: CommentsUiState
    data class Success(val comments: List<Comment>): CommentsUiState
    data class Error(val message: String): CommentsUiState
}
