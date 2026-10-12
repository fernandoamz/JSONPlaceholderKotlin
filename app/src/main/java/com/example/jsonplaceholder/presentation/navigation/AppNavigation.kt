package com.example.jsonplaceholder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jsonplaceholder.presentation.details.PostDetailsScreen
import com.example.jsonplaceholder.presentation.details.PostDetailsViewModel
import com.example.jsonplaceholder.presentation.posts.PostsScreen
import com.example.jsonplaceholder.presentation.posts.PostsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "posts"
    )
    {
        composable("posts") {
            val postsViewModel: PostsViewModel = hiltViewModel()

            PostsScreen(
                viewModel = postsViewModel,
                onPostClick = { postId ->
                    navController.navigate("postDetails/$postId")
                }
            )
        }

        composable(
            route = "postDetails/{postId}",
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.IntType
                }
            )
        ) { _ ->
            val postDetailsViewModel: PostDetailsViewModel = hiltViewModel()

            PostDetailsScreen(
                viewModel = postDetailsViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
