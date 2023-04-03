package com.exemple.foodapp.helper.recipe_list

import com.exemple.foodapp.database.RecipeService
import com.exemple.foodapp.database.RecipesDao
import com.exemple.foodapp.database.RecipesEntityMapper
import com.exemple.foodapp.domain.data.DataState
import com.exemple.foodapp.database.Recipes
import com.exemple.foodapp.database.RecipesDtoMapper
import com.exemple.foodapp.util.RECIPE_PAGINATION_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipesDao: RecipesDao,
    private val recipeService: RecipeService,
    private val entityMapper: RecipesEntityMapper,
    private val dtoMapper: RecipesDtoMapper,
) {

  fun execute(
      token: String,
      page: Int,
      query: String,
  ): Flow<DataState<List<Recipes>>> = flow {
    try {
      emit(DataState.loading())


      if (query == "error") {
          throw Exception("Search failed!")
      }

      try{
        val recipes = getRecipesNetwork(
          token = token,
          page = page,
          query = query,
        )

        recipesDao.insertRecipes(entityMapper.toEntityList(recipes))
      }catch (e: Exception){
        e.printStackTrace()
      }

      val cacheResult = if (query.isBlank()) {
        recipesDao.getAllRecipes(
            pageSize = RECIPE_PAGINATION_PAGE_SIZE,
            page = page
        )
      } else {
        recipesDao.searchRecipes(
            query = query,
            pageSize = RECIPE_PAGINATION_PAGE_SIZE,
            page = page
        )
      }

      val list = entityMapper.fromEntityList(cacheResult)

      emit(DataState.success(list))
    } catch (e: Exception) {
      emit(DataState.error<List<Recipes>>(e.message ?: "Unknown Error"))
    }
  }

  private suspend fun getRecipesNetwork(
      token: String,
      page: Int,
      query: String
  ): List<Recipes> {
    return dtoMapper.toDomainList(
        recipeService.searchRecipes(
            token = token,
            page = page,
            query = query,
        ).recipes
    )
  }
}