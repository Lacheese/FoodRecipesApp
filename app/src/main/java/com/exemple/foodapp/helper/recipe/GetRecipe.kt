package com.exemple.foodapp.helper.recipe

import com.exemple.foodapp.database.RecipeService
import com.exemple.foodapp.database.RecipesDao
import com.exemple.foodapp.database.RecipesEntityMapper
import com.exemple.foodapp.domain.data.DataState
import com.exemple.foodapp.database.Recipes
import com.exemple.foodapp.database.RecipesDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetRecipe (
    private val recipesDao: RecipesDao,
    private val entityMapper: RecipesEntityMapper,
    private val recipeService: RecipeService,
    private val recipesDtoMapper: RecipesDtoMapper,
){

  fun execute(
    recipeId: Int,
    token: String,
    isNetworkAvailable: Boolean,
  ): Flow<DataState<Recipes>> = flow {
    try {
      emit(DataState.loading())

      delay(1000)

      var recipe = getRecipesCache(recipeId = recipeId)

      if(recipe != null){
        emit(DataState.success(recipe))
      }
      else{

        if(isNetworkAvailable){
          val networkRecipe = getRecipesNetwork(token, recipeId)

          recipesDao.insertRecipe(
            entityMapper.mapFromDomainModel(networkRecipe)
          )
        }

        recipe = getRecipesCache(recipeId = recipeId)

        if(recipe != null){
          emit(DataState.success(recipe))
        }
        else{
          throw Exception("Cannot get recipe from the cache.")
        }
      }

    }catch (e: Exception){
      emit(DataState.error<Recipes>(e.message?: "Unknown Error"))
    }
  }

  private suspend fun getRecipesCache(recipeId: Int): Recipes? {
    return recipesDao.getRecipeById(recipeId)?.let { recipeEntity ->
      entityMapper.mapToDomainModel(recipeEntity)
    }
  }

  private suspend fun getRecipesNetwork(token: String, recipeId: Int): Recipes {
    return recipesDtoMapper.mapToDomainModel(recipeService.get(token, recipeId))
  }
}