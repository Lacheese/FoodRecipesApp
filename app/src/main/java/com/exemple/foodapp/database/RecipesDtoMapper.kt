package com.exemple.foodapp.database

import com.exemple.foodapp.domain.util.DomainMapper
import com.exemple.foodapp.util.DateConverter


class RecipesDtoMapper : DomainMapper<RecipesDto, Recipes> {

    override fun mapToDomainModel(model: RecipesDto): Recipes {
        return Recipes(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            ingredients = model.ingredients,
            dateAdded = DateConverter.longToDate(model.longDateAdded),
            dateUpdated = DateConverter.longToDate(model.longDateUpdated),
        )
    }

    override fun mapFromDomainModel(domainModel: Recipes): RecipesDto {
        return RecipesDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            ingredients = domainModel.ingredients,
            longDateAdded = DateConverter.dateToLong(domainModel.dateAdded),
            longDateUpdated = DateConverter.dateToLong(domainModel.dateUpdated),
        )
    }

    fun toDomainList(initial: List<RecipesDto>): List<Recipes>{
        return initial.map { mapToDomainModel(it) }
    }




}
