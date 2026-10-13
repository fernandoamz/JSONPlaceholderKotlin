package com.example.jsonplaceholder.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostDetailsScreen(
    viewModel: PostDetailsViewModel,
    onBack: () -> Unit,
    onCommentsClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }

        when (val currentState = state) {
            is PostDetailsUiState.Loading -> {
                CircularProgressIndicator()
            }

            is PostDetailsUiState.Success -> {
                Text(
                    text = currentState.post.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = currentState.post.body,
                    style = MaterialTheme.typography.bodyLarge
                )
                Button(onClick = {
                    onCommentsClick(currentState.post.id)
                }) {
                    Text("Comments")
                }
            }

            is PostDetailsUiState.Error -> {
                Text(
                    text = currentState.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
