package com.example.jsonplaceholder.presentation.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.domain.usecase.GetCommentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<CommentsUiState>(CommentsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadComments(postId: Int) {
        viewModelScope.launch {
            _uiState.value = CommentsUiState.Loading

            try {
                val comments = getCommentsUseCase(postId)
                _uiState.value = CommentsUiState.Success(comments)
            } catch (e: Exception) {
                _uiState.value = CommentsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}