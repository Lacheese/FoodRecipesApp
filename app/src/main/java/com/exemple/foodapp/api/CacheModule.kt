package com.exemple.foodapp.api

import androidx.room.Room
import com.exemple.foodapp.database.RecipesDao
import com.exemple.foodapp.database.AppDatabase
import com.exemple.foodapp.database.RecipesEntityMapper
import com.exemple.foodapp.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

  @Singleton
  @Provides
  fun dbProvider(app: BaseApplication): AppDatabase {
    return Room
      .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
      .fallbackToDestructiveMigration()
      .build()
  }

  @Singleton
  @Provides
  fun recipeDaoProvider(db: AppDatabase): RecipesDao {
    return db.recipeDao()
  }

  @Singleton
  @Provides
  fun cacheRecipeMapper(): RecipesEntityMapper {
    return RecipesEntityMapper()
  }

}







