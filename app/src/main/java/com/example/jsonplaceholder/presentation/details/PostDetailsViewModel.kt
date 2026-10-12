package com.example.jsonplaceholder.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.domain.model.Post
import com.example.jsonplaceholder.domain.usecase.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PostDetailsUiState {
    data object Loading: PostDetailsUiState
    data class Success(val post: Post): PostDetailsUiState
    data class Error(val message: String): PostDetailsUiState
}

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val postId: Int = checkNotNull(savedStateHandle["postId"])

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
