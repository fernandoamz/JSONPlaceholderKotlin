package com.example.jsonplaceholder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jsonplaceholder.di.AppContainer
import com.example.jsonplaceholder.presentation.details.PostDetailsScreen
import com.example.jsonplaceholder.presentation.details.PostDetailsViewModel
import com.example.jsonplaceholder.presentation.details.PostDetailsViewModelFactory
import com.example.jsonplaceholder.presentation.posts.PostsScreen
import com.example.jsonplaceholder.presentation.posts.PostsViewModel
import com.example.jsonplaceholder.presentation.posts.PostsViewModelFactory

@Composable
fun AppNavigation(container: AppContainer) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "posts"
    )
    {
        composable("posts") {
            val postsViewModel: PostsViewModel = viewModel(
                factory = remember(container) {
                    PostsViewModelFactory(container.getPostsUseCase)
                }
            )

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
        ) { backStackEntry ->

            val postId = requireNotNull(
                backStackEntry.arguments?.getInt("postId")
            )

            val postDetailsViewModel: PostDetailsViewModel = viewModel(
                key = "postDetailsViewModel_$postId",
                factory = remember(container, postId) {
                    PostDetailsViewModelFactory(
                        container.getPostUseCase,
                        postId
                    )
                }
            )

            PostDetailsScreen(
                viewModel = postDetailsViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
