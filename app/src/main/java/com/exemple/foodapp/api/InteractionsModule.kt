package com.exemple.foodapp.api

import com.exemple.foodapp.database.RecipeService
import com.exemple.foodapp.database.RecipesDao
import com.exemple.foodapp.database.RecipesEntityMapper
import com.exemple.foodapp.helper.recipe.GetRecipe
import com.exemple.foodapp.helper.recipe_list.RestoreRecipes
import com.exemple.foodapp.helper.recipe_list.SearchRecipes
import com.exemple.foodapp.database.RecipesDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractionsModule {

  @ViewModelScoped
  @Provides
  fun provideSearchRecipe(
    recipeService: RecipeService,
    recipesDao: RecipesDao,
    recipesEntityMapper: RecipesEntityMapper,
    recipesDtoMapper: RecipesDtoMapper,
  ): SearchRecipes {
    return SearchRecipes(
      recipeService = recipeService,
      recipesDao = recipesDao,
      entityMapper = recipesEntityMapper,
      dtoMapper = recipesDtoMapper,
    )
  }

  @ViewModelScoped
  @Provides
  fun provideRestoreRecipes(
    recipesDao: RecipesDao,
    recipesEntityMapper: RecipesEntityMapper,
  ): RestoreRecipes {
    return RestoreRecipes(
      recipesDao = recipesDao,
      entityMapper = recipesEntityMapper,
    )
  }

  @ViewModelScoped
  @Provides
  fun provideGetRecipe(
    recipesDao: RecipesDao,
    recipesEntityMapper: RecipesEntityMapper,
    recipeService: RecipeService,
    recipesDtoMapper: RecipesDtoMapper,
  ): GetRecipe {
    return GetRecipe(
      recipesDao = recipesDao,
      entityMapper = recipesEntityMapper,
      recipeService = recipeService,
      recipesDtoMapper = recipesDtoMapper,
    )
  }

}











