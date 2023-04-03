package com.exemple.foodapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipesEntity::class ], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipesDao

    companion object{
        const val DATABASE_NAME: String = "recipesData"
    }


}