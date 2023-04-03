package com.exemple.foodapp.helper.recipe_list

import com.exemple.foodapp.database.RecipesDao
import com.exemple.foodapp.database.RecipesEntityMapper
import com.exemple.foodapp.domain.data.DataState
import com.exemple.foodapp.database.Recipes
import com.exemple.foodapp.util.RECIPE_PAGINATION_PAGE_SIZE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RestoreRecipes(
    private val recipesDao: RecipesDao,
    private val entityMapper: RecipesEntityMapper,
) {
  fun execute(
    page: Int,
    query: String
  ): Flow<DataState<List<Recipes>>> = flow {
    try {
      emit(DataState.loading())

      delay(1000)

      val cacheResult = if (query.isBlank()){
        recipesDao.restoreAllRecipes(
          pageSize = RECIPE_PAGINATION_PAGE_SIZE,
          page = page
        )
      }
      else{
        recipesDao.restoreRecipes(
          query = query,
          pageSize = RECIPE_PAGINATION_PAGE_SIZE,
          page = page
        )
      }

      val list = entityMapper.fromEntityList(cacheResult)
      emit(DataState.success(list))

    }catch (e: Exception){
      emit(DataState.error<List<Recipes>>(e.message?: "Unknown Error"))
    }
  }
}




