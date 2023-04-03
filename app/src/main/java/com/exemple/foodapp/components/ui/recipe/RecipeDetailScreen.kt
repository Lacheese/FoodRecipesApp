package com.exemple.foodapp.components.ui.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.exemple.foodapp.components.RecipeView
import com.exemple.foodapp.components.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeDetailScreen(
  isNetworkAvailable: Boolean,
  recipeId: Int?,
  viewModel: RecipeViewModel,
){
  if (recipeId == null){
    TODO("Show Invalid Recipe")
  }else {
    val onLoad = viewModel.onLoad.value
    if (!onLoad) {
      viewModel.onLoad.value = true
      viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
    }

    val loading = viewModel.loading.value

    val recipe = viewModel.recipes.value


    val scaffoldState = rememberScaffoldState()

    AppTheme(
      displayProgressBar = loading,
      isNetworkAvailable = isNetworkAvailable,
    ){
      Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
          scaffoldState.snackbarHostState
        }
      ) {
        Box (
          modifier = Modifier.fillMaxSize()
        ){
          if (loading && recipe == null) {
          }
          else if(!loading && recipe == null && onLoad){
            TODO("Show Invalid Recipe")
          }
          else {
            recipe?.let { RecipeView(recipes = it) }
          }
        }
      }
    }
  }
}