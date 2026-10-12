package com.example.jsonplaceholder.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.domain.model.Post
import com.example.jsonplaceholder.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

sealed interface PostsUiState {
    data object Loading: PostsUiState
    data class Success(val posts: List<Post>): PostsUiState
    data class Error(val message: String): PostsUiState
}

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val  getPostsUseCase: GetPostsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Loading)
    val state: StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostsUiState.Loading

            try {
                val posts = getPostsUseCase()
                _uiState.value = PostsUiState.Success(posts)
            } catch (e: Exception) {
                _uiState.value = PostsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

}
