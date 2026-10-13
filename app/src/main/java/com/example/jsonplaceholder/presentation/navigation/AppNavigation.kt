package com.example.jsonplaceholder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jsonplaceholder.presentation.comments.CommentsScreen
import com.example.jsonplaceholder.presentation.details.PostDetailsScreen
import com.example.jsonplaceholder.presentation.details.PostDetailsViewModel
import com.example.jsonplaceholder.presentation.posts.PostsScreen
import com.example.jsonplaceholder.presentation.posts.PostsViewModel
import com.example.jsonplaceholder.presentation.comments.CommentsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "posts"
    )
    {
        composable("posts") {
            val viewModel: PostsViewModel = hiltViewModel()

            PostsScreen(
                viewModel = viewModel,
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
        ) {
            val viewModel: PostDetailsViewModel = hiltViewModel()

            PostDetailsScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onCommentsClick = { postId ->
                    navController.navigate("comments/$postId")
                }
            )
        }

        composable(
            route = "comments/{postId}",
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val postId = requireNotNull(
                backStackEntry.arguments?.getInt("postId")
            )

            val viewModel: CommentsViewModel = hiltViewModel()

            CommentsScreen(
                postId = postId,
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
