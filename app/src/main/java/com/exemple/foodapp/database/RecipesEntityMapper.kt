package com.exemple.foodapp.database

import com.exemple.foodapp.domain.util.DomainMapper
import com.exemple.foodapp.util.DateConverter

class RecipesEntityMapper : DomainMapper<RecipesEntity, Recipes> {

    override fun mapToDomainModel(model: RecipesEntity): Recipes {
        return Recipes(
            id = model.id,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            ingredients = ingredientsToList(model.ingredients),
            dateAdded = DateConverter.longToDate(model.dateAdded),
            dateUpdated = DateConverter.longToDate(model.dateUpdated),
        )
    }


    override fun mapFromDomainModel(domainModel: Recipes): RecipesEntity {
        return RecipesEntity(
            id = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            ingredients = ingredientToString(domainModel.ingredients),
            dateAdded = DateConverter.dateToLong(domainModel.dateAdded),
            dateUpdated = DateConverter.dateToLong(domainModel.dateUpdated),
            dateCached = DateConverter.dateToLong(DateConverter.createTimestamp())
        )
    }


    private fun ingredientToString(ingredients: List<String>): String {
        val ingredientsString = StringBuilder()
        for(ingredient in ingredients){
            ingredientsString.append("$ingredient,")
        }
        return ingredientsString.toString()
    }

    private fun ingredientsToList(ingredientsString: String?): List<String>{
        val list: ArrayList<String> = ArrayList()
        ingredientsString?.let {
            for(ingredient in it.split(",")){
                list.add(ingredient)
            }
        }
        return list
    }

    fun fromEntityList(initial: List<RecipesEntity>): List<Recipes>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Recipes>): List<RecipesEntity>{
        return initial.map { mapFromDomainModel(it) }
    }
}