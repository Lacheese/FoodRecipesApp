package com.exemple.foodapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.exemple.foodapp.components.navigation.Screen
import com.exemple.foodapp.components.ui.recipe.RecipeDetailScreen
import com.exemple.foodapp.components.ui.recipe.RecipeViewModel
import com.exemple.foodapp.components.ui.recipe_list.RecipeListScreen
import com.exemple.foodapp.components.ui.recipe_list.RecipeListViewModel
import com.exemple.foodapp.components.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

  @Inject
  lateinit var connectivityManager: ConnectivityManager


  override fun onStart() {
    super.onStart()
    connectivityManager.registerConnectionObserver(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    connectivityManager.unregisterConnectionObserver(this)
  }

  @ExperimentalComposeUiApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
          composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
            RecipeListScreen(
              isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
              onNavigateToRecipeDetailScreen = navController::navigate,
              viewModel = viewModel,
            )
          }
          composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
              type = NavType.IntType
            })
          ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeViewModel = viewModel("RecipeDetailViewModel", factory)
            RecipeDetailScreen(
              isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
              recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
              viewModel = viewModel,
            )
          }
        }

    }
  }


}














